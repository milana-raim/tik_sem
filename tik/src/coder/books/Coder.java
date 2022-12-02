package coder.books;

import java.util.*;

public class Coder {
    private final List<Character> alphabet;

    public Coder(List<Character> alphabet) {
        Collections.sort(alphabet);
        this.alphabet = alphabet;
    }

    public List<Integer> encrypt(String message) {
        List<Integer> history = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            history.add(getCodeByChar(message.charAt(i)));
        }
        return history;
    }

    private Integer getPositionByChar(Character c) {
        for (int i = 0; i < alphabet.size(); i++) {
            if (alphabet.get(i).equals(c)) {
                return i;
            }
        }
        throw new NullPointerException();
    }

    private void upInTable(Integer i) {
        Character swapChar = alphabet.get(i);
        for (int j = i; j >= 1; j--) {
            alphabet.set(j, alphabet.get(j - 1));
        }
        alphabet.set(0, swapChar);
    }

    private Integer getCodeByChar(Character c) {
        Integer position = getPositionByChar(c);
        upInTable(position);
        return position;
    }
}
