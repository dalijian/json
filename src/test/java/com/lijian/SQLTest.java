package com.lijian;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SQLTest {

    @Test
    public void test(){


        ObjectMapper mapper = new ObjectMapper();

        try {
        List<Map<String,String>>    without =  mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\like_or_without_include.json"), List.class);
            List<Map<String,String>>    with =  mapper.readValue(new FileInputStream("C:\\Users\\lijian\\IdeaProjects\\json\\src\\test\\resources\\like_or_with_include.json"), List.class);
            System.out.println("without'size" + without.size());
            System.out.println("with'size" + with.size());

            without.forEach(x->{

                Optional<Map<String, String>> option = with.stream().filter(y -> y.get("ITEM_CODE").equals(x.get("ITEM_CODE")) &&
                        y.get("CREATE_TIME").equals(x.get("CREATE_TIME")) &&
                        y.get("JGBM").equals(x.get("JGBM"))&& y.get("ITEM_NAME").equals(x.get("ITEM_NAME"))
                        &&y.get("XMLX").equals(x.get("XMLX"))
                ).findFirst();

                if (!option.isPresent()) {
                    System.out.println(x);
//                    System.out.println(option.get());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void test001(){
        String sql= "select temp_1.*, (select jgmc from t_govinfo where t_govinfo.jgbm=temp_1.jgbm) as jgmc  from ( select " +
                "SJPT_GHLS.GHKS , SJPT_GHLS.ghsj,SJPT_GHLS.sfz,SJPT_MZFYMXJL.*" +
                " FROM\n" +
                "\t\tSJPT_GHLS inner join SJPT_MZFYMXJL on SJPT_GHLS.jgbm= SJPT_MZFYMXJL.jgbm and \n" +
                "\tSJPT_GHLS.VISIT_ID=SJPT_MZFYMXJL.VISIT_ID and SJPT_GHLS.PATIENT_ID= SJPT_MZFYMXJL.PATIENT_ID\n" +
                "\t\n" +
                "\tINNER JOIN SJPT_MZFYZJL  on SJPT_GHLS.jgbm= SJPT_MZFYZJL.jgbm and \n" +
                "\tSJPT_GHLS.VISIT_ID=SJPT_MZFYZJL.VISIT_ID and SJPT_GHLS.PATIENT_ID= SJPT_MZFYZJL.PATIENT_ID\n" +
                "\tand SJPT_MZFYZJL.id=SJPT_MZFYMXJL.SF_ID\n" +
                "\t\n" +
                "\t where SJPT_MZFYMXJL.XMLX in('23') and SJPT_MZFYMXJL.item_code <> '0000003' and SJPT_MZFYMXJL.jgbm=:jgbm"+
                " and CHARGE_DATETIME  BETWEEN TO_DATE(:startTime,'yyyy-mm-dd') and TO_DATE(:endTime, 'yyyy-mm-dd') " +
                "" +
                "order by CHARGE_DATETIME desc  " +
                ") temp_1 ";
//        System.out.println(sql);

        String str =	"org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.springframework.dao.InvalidDataAccessResourceUsageException: could not extract ResultSet; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not extract ResultSet\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:982)\r\n\tat org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:648)\r\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\r\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:729)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:292)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat com.haojiankang.lion.origin.security.web.filter.AuthHandleFilter.doFilterInternal(AuthHandleFilter.java:47)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat com.haojiankang.hjkbi.had.report.filter.AuthUrlHandleFilter.doFilter(AuthUrlHandleFilter.java:71)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat com.haojiankang.lion.origin.security.web.filter.TokenHandleFilter.doFilterInternal(TokenHandleFilter.java:49)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat com.haojiankang.lion.origin.security.web.filter.ExceptionHandleFilter.doFilterInternal(ExceptionHandleFilter.java:29)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:96)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.springframework.boot.web.support.ErrorPageFilter.doFilter(ErrorPageFilter.java:117)\r\n\tat org.springframework.boot.web.support.ErrorPageFilter.access$000(ErrorPageFilter.java:61)\r\n\tat org.springframework.boot.web.support.ErrorPageFilter$1.doFilterInternal(ErrorPageFilter.java:92)\r\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\r\n\tat org.springframework.boot.web.support.ErrorPageFilter.doFilter(ErrorPageFilter.java:110)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:240)\r\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:207)\r\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:212)\r\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:106)\r\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:502)\r\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:141)\r\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79)\r\n\tat org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:616)\r\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:88)\r\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:528)\r\n\tat org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1100)\r\n\tat org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:687)\r\n\tat org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.doRun(AprEndpoint.java:2508)\r\n\tat org.apache.tomcat.util.net.AprEndpoint$SocketProcessor.run(AprEndpoint.java:2497)\r\n\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\r\n\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\r\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\r\n\tat java.lang.Thread.run(Thread.java:745)\r\nCaused by: org.springframework.dao.InvalidDataAccessResourceUsageException: could not extract ResultSet; SQL [n/a]; nested exception is org.hibernate.exception.SQLGrammarException: could not extract ResultSet\r\n\tat org.springframework.orm.hibernate5.SessionFactoryUtils.convertHibernateAccessException(SessionFactoryUtils.java:224)\r\n\tat org.springframework.orm.hibernate5.HibernateExceptionTranslator.convertHibernateAccessException(HibernateExceptionTranslator.java:68)\r\n\tat org.springframework.orm.hibernate5.HibernateExceptionTranslator.translateExceptionIfPossible(HibernateExceptionTranslator.java:49)\r\n\tat org.springframework.dao.support.ChainedPersistenceExceptionTranslator.translateExceptionIfPossible(ChainedPersistenceExceptionTranslator.java:59)\r\n\tat org.springframework.dao.support.DataAccessUtils.translateIfNecessary(DataAccessUtils.java:209)\r\n\tat org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:147)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:671)\r\n\tat com.haojiankang.hjkbi.had.report.his.dao.impl.PersonalMedicalDaoImpl$$EnhancerBySpringCGLIB$$206a07c6.queryBySql(<generated>)\r\n\tat com.haojiankang.hjkbi.had.report.his.service.impl.PersonalMedicalServiceImpl.getMZCFInfo(PersonalMedicalServiceImpl.java:185)\r\n\tat com.haojiankang.hjkbi.had.report.his.service.impl.PersonalMedicalServiceImpl.searchMZdetail(PersonalMedicalServiceImpl.java:86)\r\n\tat com.haojiankang.hjkbi.had.report.his.service.impl.PersonalMedicalServiceImpl$$FastClassBySpringCGLIB$$10d465ee.invoke(<generated>)\r\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:736)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n\tat org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)\r\n\tat org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\r\n\tat org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:671)\r\n\tat com.haojiankang.hjkbi.had.report.his.service.impl.PersonalMedicalServiceImpl$$EnhancerBySpringCGLIB$$a87e7cb9.searchMZdetail(<generated>)\r\n\tat com.haojiankang.hjkbi.had.report.his.controller.PersonalMedicalController.searchMZdetail(PersonalMedicalController.java:151)\r\n\tat com.haojiankang.hjkbi.had.report.his.controller.PersonalMedicalController$$FastClassBySpringCGLIB$$428543e6.invoke(<generated>)\r\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:736)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n\tat org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor.invoke(MethodBeforeAdviceInterceptor.java:52)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor.invoke(AfterReturningAdviceInterceptor.java:52)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\r\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:671)\r\n\tat com.haojiankang.hjkbi.had.report.his.controller.PersonalMedicalController$$EnhancerBySpringCGLIB$$b2e3ba1a.searchMZdetail(<generated>)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.lang.reflect.Method.invoke(Method.java:498)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\r\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\r\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\r\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:967)\r\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:901)\r\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\r\n\t... 67 more\r\nCaused by: org.hibernate.exception.SQLGrammarException: could not extract ResultSet\r\n\tat org.hibernate.exception.internal.SQLExceptionTypeDelegate.convert(SQLExceptionTypeDelegate.java:63)\r\n\tat org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:42)\r\n\tat org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:109)\r\n\tat org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:95)\r\n\tat org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.extract(ResultSetReturnImpl.java:79)\r\n\tat org.hibernate.loader.Loader.getResultSet(Loader.java:2117)\r\n\tat org.hibernate.loader.Loader.executeQueryStatement(Loader.java:1900)\r\n\tat org.hibernate.loader.Loader.executeQueryStatement(Loader.java:1876)\r\n\tat org.hibernate.loader.Loader.doQuery(Loader.java:919)\r\n\tat org.hibernate.loader.Loader.doQueryAndInitializeNonLazyCollections(Loader.java:336)\r\n\tat org.hibernate.loader.Loader.doList(Loader.java:2617)\r\n\tat org.hibernate.loader.Loader.doList(Loader.java:2600)\r\n\tat org.hibernate.loader.Loader.listIgnoreQueryCache(Loader.java:2429)\r\n\tat org.hibernate.loader.Loader.list(Loader.java:2424)\r\n\tat org.hibernate.loader.custom.CustomLoader.list(CustomLoader.java:336)\r\n\tat org.hibernate.internal.SessionImpl.listCustomQuery(SessionImpl.java:1967)\r\n\tat org.hibernate.internal.AbstractSessionImpl.list(AbstractSessionImpl.java:322)\r\n\tat org.hibernate.internal.SQLQueryImpl.list(SQLQueryImpl.java:125)\r\n\tat com.haojiankang.lion.origin.mvc.dao.BaseDaoImpl.queryBySql(BaseDaoImpl.java:613)\r\n\tat com.haojiankang.lion.origin.mvc.dao.BaseDaoImpl$$FastClassBySpringCGLIB$$e6c35771.invoke(<generated>)\r\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\r\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:736)\r\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\r\n\tat org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:136)\r\n\t... 112 more\r\nCaused by: java.sql.SQLSyntaxErrorException: ORA-00904: \"A\".\"HANDLETIME\": 标识符无效\n\r\n\tat oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:439)\r\n\tat oracle.jdbc.driver.T4CTTIoer.processError(T4CTTIoer.java:395)\r\n\tat oracle.jdbc.driver.T4C8Oall.processError(T4C8Oall.java:802)\r\n\tat oracle.jdbc.driver.T4CTTIfun.receive(T4CTTIfun.java:436)\r\n\tat oracle.jdbc.driver.T4CTTIfun.doRPC(T4CTTIfun.java:186)\r\n\tat oracle.jdbc.driver.T4C8Oall.doOALL(T4C8Oall.java:521)\r\n\tat oracle.jdbc.driver.T4CPreparedStatement.doOall8(T4CPreparedStatement.java:205)\r\n\tat oracle.jdbc.driver.T4CPreparedStatement.executeForDescribe(T4CPreparedStatement.java:861)\r\n\tat oracle.jdbc.driver.OracleStatement.executeMaybeDescribe(OracleStatement.java:1145)\r\n\tat oracle.jdbc.driver.OracleStatement.doExecuteWithTimeout(OracleStatement.java:1267)\r\n\tat oracle.jdbc.driver.OraclePreparedStatement.executeInternal(OraclePreparedStatement.java:3449)\r\n\tat oracle.jdbc.driver.OraclePreparedStatement.executeQuery(OraclePreparedStatement.java:3493)\r\n\tat oracle.jdbc.driver.OraclePreparedStatementWrapper.executeQuery(OraclePreparedStatementWrapper.java:1491)\r\n\tat com.alibaba.druid.pool.DruidPooledPreparedStatement.executeQuery(DruidPooledPreparedStatement.java:228)\r\n\tat org.hibernate.engine.jdbc.internal.ResultSetReturnImpl.extract(ResultSetReturnImpl.java:70)\r\n\t... 131 more\r\n";

        System.out.println(str);

    }
}