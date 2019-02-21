package parser;

public class Lexeme {
    private LexemeType type;
    private String text;

    public Lexeme(LexemeType type, String text) {
        this.type = type;
        this.text = text;
    }

    public LexemeType getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
