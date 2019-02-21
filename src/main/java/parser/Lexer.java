package parser;

import parser.exception.LexemeParseException;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int current;

    public Lexer(Reader reader) throws IOException {
        current = reader.read();
        this.reader = reader;
    }

    Lexeme getLexeme() throws IOException, LexemeParseException {
        while (current == ' ') {
            current = reader.read();
        }
        switch (current) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(current)) {
                    sb.append((char)current);
                    current = reader.read();
                }
                return new Lexeme(LexemeType.NUMBER, sb.toString());
            case -1:
                return new Lexeme(LexemeType.EOF, String.valueOf(current));
            case '-':
                current = reader.read();
                return new Lexeme(LexemeType.MINUS, "-");
            case '+':
                current = reader.read();
                return new Lexeme(LexemeType.PLUS, "+");
            case '(':
                current = reader.read();
                return new Lexeme(LexemeType.OPEN, "(");
            case ')':
                current = reader.read();
                return new Lexeme(LexemeType.CLOSE, ")");
            case '^':
                current = reader.read();
                return new Lexeme(LexemeType.POWER, "^");
            case '*':
                current = reader.read();
                return new Lexeme(LexemeType.MULTIPLY, "*");
            case '/':
                current = reader.read();
                return new Lexeme(LexemeType.DIVIDE, "/");
            default:
                throw new LexemeParseException("Unknown lexeme " + String.valueOf((char)current));
        }
    }
}
