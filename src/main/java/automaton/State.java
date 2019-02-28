package automaton;

import java.util.ArrayList;
import java.util.List;

public class State {

    private boolean isFinal = false;
    private List<Segue> segues = new ArrayList<>();

    public void addSegue(Segue segue) {
        segues.add(segue);
    }

    public int getNext(char symbol) {
        for (Segue segue: segues) {
            if (segue.getSymbol() == symbol) {
                return segue.getTo();
            }
        }
        return -1;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isFinal() {
        return isFinal;
    }
}
