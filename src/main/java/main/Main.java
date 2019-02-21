package main;

import parser.Lexer;
import parser.Parser;
import parser.exception.ExpressionParseException;
import parser.exception.LexemeParseException;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Parser parser = new Parser(new Lexer(new FileReader("test.txt")));
            System.out.println(parser.parseExpr());
        } catch (IOException | LexemeParseException | ExpressionParseException e) {
            e.printStackTrace();
        }
    }
}
