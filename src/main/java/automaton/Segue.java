package automaton;

public class Segue {
    private int from;
    private int to;
    private char symbol;

    public Segue(int from, int to, char symbol) {
        this.from = from;
        this.to = to;
        this.symbol = symbol;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public char getSymbol() {
        return symbol;
    }
}
