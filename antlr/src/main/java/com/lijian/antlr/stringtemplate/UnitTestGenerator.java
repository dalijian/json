package com.lijian.antlr.stringtemplate;

import com.lijian.antlr.stringtemplate.JavaParserBaseListener;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.compiler.CompiledST;
import org.stringtemplate.v4.compiler.FormalArgument;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple unit test generator.
 */
public class UnitTestGenerator extends JavaParserBaseListener {

    /** Constants: template name, placeholder name, generated code name */
    private static final String CLASS_ST_NAME = "classST";
    private static final String METHOD_ST_NAME = "methodST";

    private static final String TEST_PKG_NAME = "testPkgName";
    private static final String TEST_CLASS_NAME = "testClassName";
    private static final String TEST_METHOD_NAME = "testMethodName";

    private static final String CLASS_NAME_SUFFIX = "Test";
    private static final String METHOD_NAME_PREFIX = "test";

    /** Template for Java */
    private static final String METHOD_ST =
            t("@Test") +
                    t("public void " + $(TEST_METHOD_NAME) + "() throws Exception {") +
                    tt("//body...") +
                    t("}") +
                    n("");

    private static final String CLASS_ST =
            n("package " + $(TEST_PKG_NAME) + ";") +
                    n("") +
                    n("import org.junit.*;") +
                    n("") +
                    n("public class " + $(TEST_CLASS_NAME) + " {") +
                    n("") +
                    /**
                     * Apply nested template 'methodST' to multi-valued attributes 'testMethodName'.
                     * NOTE: <attribute:template(argument-list)>
                     *      Apply template to attribute with optional argument-list.
                     *      Example: <name:bold()> applies bold() to name's value.
                     *      The first argument of the template gets the iterated value.
                     */
                    n($(TEST_METHOD_NAME + ":" + METHOD_ST_NAME +"();separator=\"\n\"")) +
                    n("}");

    /** ST group to nest template */
    private static STGroup group;
    static {
        group = new STGroup('$', '$');

        CompiledST classST = group.defineTemplate(CLASS_ST_NAME, CLASS_ST);
        classST.addArg(new FormalArgument(TEST_PKG_NAME));
        classST.addArg(new FormalArgument(TEST_CLASS_NAME));
        classST.addArg(new FormalArgument(TEST_METHOD_NAME));

        CompiledST methodST = group.defineTemplate(METHOD_ST_NAME, METHOD_ST);
        methodST.addArg(new FormalArgument(TEST_METHOD_NAME));
    }

    /** Attributes. NOTE: group.getInstanceOf() return new ST */
    private Map<String, Object> attributeMap = new HashMap<>();

    @Override
    public void enterPackageDeclaration(@NotNull JavaParser.PackageDeclarationContext ctx) {
        attributeMap.put(TEST_PKG_NAME, ctx.qualifiedName().getText());
    }

    @Override
    public void enterClassDeclaration(@NotNull JavaParser.ClassDeclarationContext ctx) {
//        String orgClassName = ctx.Identifier().getText();
    String     orgClassName=ctx.CLASS().getText();
        String testClassName = orgClassName + CLASS_NAME_SUFFIX;

        attributeMap.put(TEST_CLASS_NAME, testClassName);
    }


    @Override
    public void enterMethodDeclaration(@NotNull JavaParser.MethodDeclarationContext ctx) {
//        String orgMethodName = ctx.Identifier().getText();
    String     orgMethodName= ctx.methodBody().getText();
        String testMethodName = METHOD_NAME_PREFIX + orgMethodName.substring(0, 1).toUpperCase()
                + orgMethodName.substring(1);

        // Multi-valued attribute
        List<String> methodNames = (List<String>) attributeMap.get(TEST_METHOD_NAME);
        if (methodNames == null) {
            methodNames = new ArrayList<>();
            attributeMap.put(TEST_METHOD_NAME, methodNames);
        }
        methodNames.add(testMethodName);
    }

    @Override
    public void enterStatement(@NotNull JavaParser.StatementContext ctx) {
        // If/else/switch, for/while, try-catch
        switch (ctx.getStart().getText()) {
            case "if":
                break;
            case "for":
                break;
            case "try":
                break;
            default:
                break;
        }
    }

    @Override
    public void exitCompilationUnit(@NotNull JavaParser.CompilationUnitContext ctx) {
        ST template = group.getInstanceOf(CLASS_ST_NAME);
        attributeMap.entrySet().forEach(e -> template.add(e.getKey(), e.getValue()));
        System.out.println(template.render());

        // Cleanup
        attributeMap.clear();
    }

    // =======================================
    //          String Utility
    // =======================================

    private static String $(String attrName) {
        return "$" + attrName + "$";
    }

    private static String n(String str) {
        return str + "\n";
    }

    private static String t(String str) {
        return "\t" + n(str);
    }

    private static String tt(String str) {
        return "\t\t" + n(str);
    }

}