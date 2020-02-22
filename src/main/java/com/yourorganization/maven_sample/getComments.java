package com.yourorganization.maven_sample;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;

import java.io.File;
import java.util.List;

/**
 * Show an error on where the comment starts. It doesn't take into account the first line
 */
public class getComments {
    private static final String FILE_PATH = "src/main/resources/aPackage/commentBeforePackage.java";
    private static final String FILE_PATH2 = "src/main/resources/aPackage/commentBeforePackageBlock.java";

    public static void main(String[] args) throws Exception {
        CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
        List<Comment> comments = cu.getAllContainedComments();
        System.out.println(comments);

        CompilationUnit cu2 = StaticJavaParser.parse(new File(FILE_PATH2));
        List<Comment> comments2 = cu2.getAllContainedComments();
        System.out.println(comments2);
    }
}
