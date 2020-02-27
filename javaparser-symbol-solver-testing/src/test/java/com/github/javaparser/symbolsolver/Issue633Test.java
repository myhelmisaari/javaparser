package com.github.javaparser.symbolsolver;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.ReturnStmt;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The JavaParser wiki says if a JavaDoc-comment is preceeding an annotation, it will be connected
 * to the type of the method and not the actual method. This is due to that the annotation will note the first line
 * of the method. Note: when coding and using IDE's such as IntelliJ and Eclipse, comments between the annotation
 * and the method are marked as "dangling comments", so if you code correctly it should not be placed there.
 *
 * However, the fact that the comment is connected to the Type of the method makes no sense. It should belong to
 * the method. This is what the issue is about.
 */
public class Issue633Test {

    /**
     * Tests if the method contains a comment
     */
    @Test
    void testMethodContainsComment() throws Exception {
        MethodDeclaration method = getMethodDeclaration();
        assertTrue(method.getComment().isPresent());
    }

    /**
     * If the comment should be associated with the method, the type should not have any comment.
     */
    @Test
    void testTypeDoesNotContainComment() throws Exception {
        MethodDeclaration method = getMethodDeclaration();
        assertFalse(method.getType().getComment().isPresent());
    }

    /**
     * In the issue discussion it is mention that return statements that return something of array-type won't have
     * the comment attached to it, but it already passes so this part of the issue must already be fixed.
     */
    @Test
    void testReturnArrayContainComment() throws Exception {
        String src =
                "public class Test {\n" +
                        "    @Test\n" +
                        "    public void test() {\n" +
                        "    String[] s = new String[3];\n" +
                        "    /**\n" +
                        "      * JavaDoc\n" +
                        "      */\n" +
                        "     return s;" +
                        "}\n" +
                        "}";

        CompilationUnit unit = StaticJavaParser.parse(src);
        ReturnStmt returnStmt = (ReturnStmt) unit
                .getChildNodes().get(0)
                .getChildNodes().get(2)
                .getChildNodes().get(4)
                .getChildNodes().get(1);

        assertTrue(returnStmt.getComment().isPresent());
    }

    private MethodDeclaration getMethodDeclaration(){
        String src =
                "public class Test {\n" +
                        "    @Test\n" +
                        "    /**\n" +
                        "      * JavaDoc\n" +
                        "      */\n" +
                        "    public void test() {}\n" +
                        "}";

        CompilationUnit unit = StaticJavaParser.parse(src);

        MethodDeclaration method = (MethodDeclaration) unit
                .getTypes().get(0)
                .getMembers().get(0);

        return method;
    }
}
