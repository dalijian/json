package com.lijian.reflections;

import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.PathParam;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ReflectionTest {

    static Logger log = LoggerFactory.getLogger(ReflectionTest.class);
    private Connection connection;

//    @Before
    public void getConnection(){
        try {
            loadJdbcDrivers();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() throws MalformedURLException {
        String relativelyPath = System.getProperty("user.dir");
        URL classPath = Thread.currentThread().getContextClassLoader().getResource(".");
        ConfigurationBuilder builder = new ConfigurationBuilder().setUrls(classPath);
        Reflections reflections = new Reflections(builder);
    }




//    支持 载入 指定 class
    private void loadJdbcDrivers() throws MalformedURLException {
        URL url=   Paths.get("C:/users/lijian/desktop/classLoader/").toUri().toURL();
        URLClassLoader loader = new URLClassLoader(Stream.of(url).toArray(URL[]::new));
        System.out.println("开始扫描指定路径");
        // Apply here what java.sql.DriverManager does to discover and register classes
        // implementing the java.sql.Driver interface.
        AccessController.doPrivileged(
                new PrivilegedAction<Void>() {
                    @Override
                    public Void run() {

                        ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(
                                Driver.class,
                                loader
                        );
                        Iterator<Driver> driversIterator = loadedDrivers.iterator();
                        try {
                            while (driversIterator.hasNext()) {
                                Driver driver = driversIterator.next();
                                System.out.println(
                                        "Registered java.sql.Driver: {} to java.sql.DriverManager " +
                                                driver
                                );
                            }
                        } catch (Throwable t) {
                            System.out.println(
                                    "Ignoring java.sql.Driver classes listed in resources but not"
                                            + " present in class loader's classpath: " +
                                            t
                            );
                        }
                        return null;
                    }
                }
        );
        String mysqlUrl = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "645143";
        try {
            this.connection=  DriverManager.getConnection(mysqlUrl, username, password);
            boolean flag = connection.isValid(1);
            System.out.println(flag);
            PreparedStatement ps = connection.prepareStatement("show tables");
            ResultSet rs = ps.executeQuery();
            List<Map<String, Object>> result = convertList(rs);
            System.out.println(result);
            System.out.println("********************");
            String incrementColumn = getAutoincrementColumn(connection, null, "kafka_mysql");
            System.out.println(incrementColumn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }








    @Test
    public void test_1() throws MalformedURLException {
//        URL url=   Paths.get("C:/users/lijian/desktop/classLoader/").toUri().toURL();
        URL url = new URL("jar:file:/C:/users/lijian/desktop/classLoader/mysql-connector-java-8.0.20.jar!/");
        URLClassLoader loader = new URLClassLoader(Stream.of(url).toArray(URL[]::new));
        // 扫包
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .addClassLoader(loader)
                .addUrls(url)
//                .forPackages("C:/users/lijian/desktop/classLoader/") // 指定路径URL
                .addScanners(new SubTypesScanner()) // 添加子类扫描工具
                .addScanners(new FieldAnnotationsScanner()) // 添加 属性注解扫描工具
                .addScanners(new MethodAnnotationsScanner()) // 添加 方法注解扫描工具
                .addScanners(new MethodParameterScanner()) // 添加方法参数扫描工具
        );
        // 反射出子类
        Set<Class<? extends Driver>> set = reflections.getSubTypesOf(Driver.class);
        System.out.println("getSubTypesOf:" + set);

        // 反射出带有指定注解的类
        Set<Class<?>> ss = reflections.getTypesAnnotatedWith(MyAnnotation.class);
        System.out.println("getTypesAnnotatedWith:" + ss);

        // 获取带有特定注解对应的方法
        Set<Method> methods = reflections.getMethodsAnnotatedWith(MyMethodAnnotation.class);
        System.out.println("getMethodsAnnotatedWith:" + methods);

        // 获取带有特定注解对应的字段
        Set<Field> fields = reflections.getFieldsAnnotatedWith(Autowired.class);
        System.out.println("getFieldsAnnotatedWith:" + fields);

        // 获取特定参数对应的方法
        Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
        System.out.println("getMethodsMatchParams:" + someMethods);

        Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
        System.out.println("getMethodsReturn:" + voidMethods);

        Set<Method> pathParamMethods = reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
        System.out.println("getMethodsWithAnyParamAnnotated:" + pathParamMethods);
    }




    public  static List<Map<String, Object>> convertList(ResultSet rs) throws SQLException {
        List<Map<String, Object>> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();//获取键名
        int columnCount = md.getColumnCount();//获取列的数量
        while (rs.next()) {
            Map<String, Object> rowData = new LinkedHashMap<String, Object>();//声明Map
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toLowerCase(), rs.getObject(i));//获取键名及值
            }
            list.add(rowData);
        }
        return list;
        }

//        [{table_cat=test, table_schem=null, table_name=kafka_mysql, column_name=one, data_type=4, type_name=INT, column_size=10, buffer_length=65535, decimal_digits=0, num_prec_radix=10, nullable=0, remarks=, column_def=null, sql_data_type=0, sql_datetime_sub=0, char_octet_length=null, ordinal_position=1, is_nullable=NO, scope_catalog=null, scope_schema=null, scope_table=null, source_data_type=null, is_autoincrement=YES, is_generatedcolumn=NO}, {table_cat=test, table_schem=null, table_name=kafka_mysql, column_name=version, data_type=12, type_name=VARCHAR, column_size=255, buffer_length=65535, decimal_digits=null, num_prec_radix=10, nullable=1, remarks=, column_def=null, sql_data_type=0, sql_datetime_sub=0, char_octet_length=255, ordinal_position=2, is_nullable=YES, scope_catalog=null, scope_schema=null, scope_table=null, source_data_type=null, is_autoincrement=NO, is_generatedcolumn=NO}, {table_cat=test, table_schem=null, table_name=kafka_mysql, column_name=createtime, data_type=93, type_name=DATETIME, column_size=19, buffer_length=65535, decimal_digits=null, num_prec_radix=10, nullable=1, remarks=, column_def=CURRENT_TIMESTAMP, sql_data_type=0, sql_datetime_sub=0, char_octet_length=null, ordinal_position=3, is_nullable=YES, scope_catalog=null, scope_schema=null, scope_table=null, source_data_type=null, is_autoincrement=NO, is_generatedcolumn=NO}]
    // 拿到 table 主键
    /**
     * Look up the autoincrement column for the specified table.
     * @param conn database connection
     * @param table the table to
     * @return the name of the column that is an autoincrement column, or null if there is no
     *         autoincrement column or more than one exists
     * @throws SQLException if there is an error with the database connection
     */
    public static String getAutoincrementColumn(
            Connection conn,
            String schemaPattern,
            String table
    ) throws SQLException {
        String result = null;
        int matches = 0;

        try (ResultSet rs = conn.getMetaData().getColumns(null, schemaPattern, table, "%")) {
            // Some database drivers (SQLite) don't include all the columns
            if (rs.getMetaData().getColumnCount() >= GET_COLUMNS_IS_AUTOINCREMENT) {
                while (rs.next()) {
                    if (rs.getString(GET_COLUMNS_IS_AUTOINCREMENT).equals("YES")) {
                        result = rs.getString(GET_COLUMNS_COLUMN_NAME);
                        matches++;
                    }
                }
                return (matches == 1 ? result : null);
            }
        }

        // Fallback approach is to query for a single row. This unfortunately does not work with any
        // empty table
        log.trace("Falling back to SELECT detection of auto-increment column for {}:{}", conn, table);
        try (Statement stmt = conn.createStatement()) {
            String quoteString = getIdentifierQuoteString(conn);
            ResultSet rs = stmt.executeQuery(
                    "SELECT * FROM " + quoteString + table + quoteString + " LIMIT 1"
            );
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i < rsmd.getColumnCount(); i++) {
                if (rsmd.isAutoIncrement(i)) {
                    result = rsmd.getColumnName(i);
                    matches++;
                }
            }
        }
        return (matches == 1 ? result : null);
    }


    @Test
    public void sql_dialect_Test(){
        try {
            System.out.println("拿到 指定 引号 ");
            String dialect = getIdentifierQuoteString(connection);
            System.out.println(dialect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get the string used for quoting identifiers in this database's SQL dialect.
     * @param connection the database connection
     * @return the quote string
     * @throws SQLException if there is an error with the database connection
     */
    public static String getIdentifierQuoteString(Connection connection) throws SQLException {
        String quoteString = connection.getMetaData().getIdentifierQuoteString();
        quoteString = quoteString == null ? "" : quoteString;
        return quoteString;
    }


    private static final int GET_TABLES_TYPE_COLUMN = 4;
    private static final int GET_TABLES_NAME_COLUMN = 3;

    private static final int GET_COLUMNS_COLUMN_NAME = 4;
    private static final int GET_COLUMNS_IS_NULLABLE = 18;
    private static final int GET_COLUMNS_IS_AUTOINCREMENT = 23;



    @Test
    public void updateTableTest(){


        try {
            List<String> tableList = getTables(connection, null, new CopyOnWriteArraySet<>());
            System.out.println(tableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Get a list of table names in the database.
     * @param conn database connection
     * @param types a set of table types that should be included in the results; may not be null
     *              but may be empty if the tables should be returned regardless of their type
     * @return a list of tables; never null
     * @throws SQLException if there is an error with the database connection
     */
    public static List<String> getTables(
            Connection conn,
            String schemaPattern,
            Set<String> types
    ) throws SQLException {
        DatabaseMetaData metadata = conn.getMetaData();
        String[] tableTypes = types.isEmpty() ? null : getActualTableTypes(metadata, types);

        try (ResultSet rs = metadata.getTables(null, schemaPattern, "%", tableTypes)) {
            List<String> tableNames = new ArrayList<>();
            while (rs.next()) {
                String colName = rs.getString(GET_TABLES_NAME_COLUMN);
                // SQLite JDBC driver does not correctly mark these as system tables
                if (metadata.getDatabaseProductName().equals("SQLite") && colName.startsWith("sqlite_")) {
                    continue;
                }

                tableNames.add(colName);
            }
            return tableNames;
        }
    }


    /**
     * Find the available table types that are returned by the JDBC driver that case insensitively
     * match the specified types.
     *
     * @param metadata the database metadata; may not be null but may be empty if no table types
     * @param types the case-independent table types
     * @return the array of table types take directly from the list of available types returned by
     *         the JDBC driver; never null
     * @throws SQLException if there is an error with the database connection
     */
    protected static String[] getActualTableTypes(
            DatabaseMetaData metadata,
            Set<String> types
    ) throws SQLException {
        // Compute the uppercase form of the desired types ...
        Set<String> uppercaseTypes = new HashSet<>();
        for (String type : types) {
            if (type != null) {
                uppercaseTypes.add(type.toUpperCase());
            }
        }
        // Now find out the available table types ...
        Set<String> matchingTableTypes = new HashSet<>();
        try (ResultSet rs = metadata.getTableTypes()) {
            while (rs.next()) {
                String tableType = rs.getString(1);
                if (tableType != null && uppercaseTypes.contains(tableType.toUpperCase())) {
                    matchingTableTypes.add(tableType);
                }
            }
        }
        return matchingTableTypes.toArray(new String[matchingTableTypes.size()]);
    }
}
