package main;

import automaton.Segue;
import automaton.State;
import automaton.StateMachine;
import exception.AutomatonException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("provide args: stateMachineConfigurationFilePath and stringToRecognizeFilePath");
            return;
        }
        Map<Integer, State> states = parseStateMachineFile(args[0]);
        if (states == null) {
            System.out.println("wrong configuration file format");
            return;
        }
        try {
            StateMachine stateMachine = new StateMachine(states);
            String stringToRecognize = getStringFromFile(args[1]);
            if (stringToRecognize == null) {
                System.out.println("string file not found or has wrong format");
                return;
            }
            System.out.println(stateMachine.recognizeString(stringToRecognize));
        } catch (AutomatonException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Map<Integer, State> parseStateMachineFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        Map<Integer, State> map = new HashMap<>();
        Scanner scanner = new Scanner(file);
        String[] finalStates = {};
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            finalStates = s.split(" ");
        }
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] segue = s.split(" ");
            if (segue.length != 3) {
                return null;
            }
            if (segue[1].length() != 1) {
                return null;
            }
            int from = Integer.valueOf(segue[0]);
            int to = Integer.valueOf(segue[2]);
            char symbol = segue[1].charAt(0);
            if (map.get(from) == null) {
                map.put(from, new State());
            }
            map.get(from).addSegue(new Segue(from, to, symbol));
        }
        for (String s: finalStates) {
            int num = Integer.valueOf(s);
            if (map.get(num) == null) {
                map.put(num, new State());
            }
            map.get(num).setFinal(true);
        }
        return map;
    }

    private static String getStringFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }

    }
}
