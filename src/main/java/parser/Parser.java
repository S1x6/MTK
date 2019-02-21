package parser;

import parser.exception.ExpressionParseException;
import parser.exception.LexemeParseException;

import java.io.IOException;

public class Parser {

    private Lexeme current;
    private Lexer lexer;

    public Parser(Lexer lexer) throws IOException, LexemeParseException {
        current = lexer.getLexeme();
        this.lexer = lexer;
    }

    int parseAtom() throws IOException, LexemeParseException, ExpressionParseException {
        if (current.getType() == LexemeType.NUMBER) {
            int temp = Integer.parseInt(current.getText());
            current = lexer.getLexeme();
            return temp;
        }
        if (current.getType() == LexemeType.OPEN) {
            current = lexer.getLexeme();
            int temp = parseExpr();
            if (current.getType() != LexemeType.CLOSE) {
                throw new ExpressionParseException("Не удалось распарсить выражение. Ожидалась закрывающая скобка");
            }
            lexer.getLexeme();
            return temp;
        }
        throw new ExpressionParseException("Не удалось распарсить выражение. Ожидалось число или открывающаяся скобка");
    }

    int parsePower() throws IOException, LexemeParseException, ExpressionParseException {
        if (current.getType() == LexemeType.MINUS) {
            current = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    int parseFactor() throws LexemeParseException, ExpressionParseException, IOException {
        int temp = parsePower();
        if (current.getType() == LexemeType.POWER) {
            current = lexer.getLexeme();
            return (int) Math.pow(temp, parseFactor());
        }
        return temp;
    }

    int parseTerm() throws IOException, ExpressionParseException, LexemeParseException {
        int value = parseFactor();
        while ((current.getType() == LexemeType.MULTIPLY) || (current.getType() == LexemeType.DIVIDE)) {
            if (current.getType() == LexemeType.MULTIPLY) {
                current = lexer.getLexeme();
                value *= parseFactor();
            } else {
                current = lexer.getLexeme();
                value /= parseFactor();
            }
        }
        return value;
    }

    public int parseExpr() throws LexemeParseException, ExpressionParseException, IOException {
        int temp = parseTerm();
        while (current.getType() == LexemeType.PLUS || current.getType() == LexemeType.MINUS) {

            if (current.getType() == LexemeType.PLUS) {
                current = lexer.getLexeme();
                temp += parseTerm();
            } else {
                current = lexer.getLexeme();
                temp -= parseTerm();
            }
        }
        return temp;
    }

}
