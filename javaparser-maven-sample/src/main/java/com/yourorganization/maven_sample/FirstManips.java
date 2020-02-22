package com.yourorganization.maven_sample;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class FirstManips {
    private static final String FILE_PATH = "src/main/resources/aPackage/commentBeforePackage.java";
    private static final String FILE_PATH2 = "src/main/resources/aPackage/commentBeforePackageBlock.java";
    private static final String FILE_PATH3 = "src/main/resources/aPackage/oneCommentBeforePackage.java";

    public static void main(String[] args) throws Exception {
        getPackage();
        System.out.println("**************************************************");
        getComments();
    }
    public static void getPackage() throws Exception{
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
        System.out.println(" * Package declaration gotten on a file with 3 line comments before package declaration");
        System.out.println("    * "+cu.getPackageDeclaration());
        CompilationUnit cu2 = StaticJavaParser.parse(new File(FILE_PATH2));
        System.out.println(" * Package declaration gotten on a file with 2 block comments before package declaration");
        System.out.println("    * "+cu2.getPackageDeclaration());
        CompilationUnit cu3 = StaticJavaParser.parse(new File(FILE_PATH3));
        System.out.println(" * Package declaration gotten on a file with 1 line comment before package declaration");
        System.out.println("    * "+cu3.getPackageDeclaration());
    }
    public static void getComments() throws Exception{
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
        List<Comment> comments = cu.getAllContainedComments();
        System.out.println(" * Comments gotten with 'getAllContainedComments' on a file with 3 line comments before package declaration");
        System.out.println("   * "+comments);

        CompilationUnit cu2 = StaticJavaParser.parse(new File(FILE_PATH2));
        List<Comment> comments2 = cu2.getAllContainedComments();
        System.out.println(" * Comments gotten with 'getAllContainedComments' on a file with 2 block comments before package declaration");
        System.out.println("   * "+comments2);

        CompilationUnit cu3 = StaticJavaParser.parse(new File(FILE_PATH2));
        Optional<Comment> comments3 = cu3.getComment();
        System.out.println(" * First comment of the file gotten with 'getComment' on a file with 2 block comments before package declaration");
        System.out.println("   * "+comments3);

        CompilationUnit cu4 = StaticJavaParser.parse(new File(FILE_PATH2));
        List<Comment> comments4 = cu4.getComments();
        System.out.println(" * Comments gotten with 'getComments' on a file with 2 block comments before package declaration");
        System.out.println("   * "+comments2);

        CompilationUnit cu5 = StaticJavaParser.parse(new File(FILE_PATH3));
        List<Comment> comments5 = cu.getAllContainedComments();
        System.out.println(" * Comments gotten with 'getAllContainedComments' on a file with 1 line comment before package declaration");
        System.out.println("   * "+comments5);
    }
}
