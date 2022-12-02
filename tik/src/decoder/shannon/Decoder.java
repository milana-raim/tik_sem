package decoder.shannon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Decoder {
    private final List<Character> alphabet;
    private final List<Double> p;
    private final List<Double> q = new ArrayList<>();
    private final List<String> codes = new ArrayList<>();

    public Decoder(List<Character> alphabet, List<Double> p) {
        sortProbabilities(alphabet, p);
        for (int i = 0; i < p.size(); i++) {
            int l = (int) Math.ceil(Math.log(1 / p.get(i)) / Math.log(2));
            String code = "";
            double curQ = 0.0;
            if (i != 0) {
                curQ = Double.sum(q.get(i - 1), p.get(i - 1));
                code = fractionalToBinary(curQ, l);
            }
            while (code.length() < l) {
                code = code.concat("0");
            }
            codes.add(code);
            q.add((double) Math.round(curQ * 1000) / 1000);
        }
        /*printCodes(alphabet);*/
        this.p = p;
        this.alphabet = alphabet;
    }

    private void sortProbabilities(List<Character> alphabet, List<Double> p) {
        for (int i = 0; i < alphabet.size(); i++) {
            for (int j = i + 1; j < alphabet.size(); j++) {
                if (p.get(i) < p.get(j) || (Objects.equals(p.get(i), p.get(j)) && alphabet.get(j) < alphabet.get(i))) {
                    double swap = p.get(i);
                    p.set(i, p.get(j));
                    p.set(j, swap);

                    Character swapC = alphabet.get(j);
                    alphabet.set(j, alphabet.get(i));
                    alphabet.set(i, swapC);
                }
            }
        }
    }

    private void printCodes(List<Character> alphabet) {
        for (int i = 0; i < alphabet.size(); i++) {
            System.out.println(alphabet.get(i) + " " + codes.get(i));
        }
    }


    public List<Character> decrypt(String message) {
        List<Character> chars = new ArrayList<>();
        int l = 0;
        int r = 1;
        while (r != message.length() + 1) {
            for (int i = 0; i < alphabet.size(); i++) {
                if (codes.get(i).equals(message.substring(l, r))) {
                    chars.add(alphabet.get(i));
                    l = r;
                }
            }
            r++;
        }
        return chars;
    }

    private String fractionalToBinary(double num, int precision) {
        StringBuilder binary = new StringBuilder();
        while (num > 0 && binary.length() < precision) {
            double r = num * 2;
            if (r >= 1) {
                binary.append(1);
                num = r - 1;
            } else {
                binary.append(0);
                num = r;
            }
        }
        return binary.toString();
    }
}
