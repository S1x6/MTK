package automaton;

import exception.AutomatonException;

import java.util.Map;

public class StateMachine {

    private Map<Integer, State> states;
    private State currentState;


    public StateMachine(Map<Integer, State> states) throws AutomatonException {
        if (states.get(1) == null) {
            throw new AutomatonException("No initial state found");
        }
        this.states = states;
    }

    public boolean recognizeString(String s) {
        currentState = states.get(1);
        for (int i = 0; i < s.length(); ++i) {
            State state = states.get(currentState.getNext(s.charAt(i)));
            if (state == null) {
                return false;
            }
            currentState = state;
        }
        return currentState.isFinal();
    }

}
