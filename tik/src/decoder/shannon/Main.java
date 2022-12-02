package decoder.shannon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final List<Character> chars = new ArrayList<>();

    private static final List<Double> probability = new ArrayList<>();

    public static void main(String[] args) {


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Milana\\Desktop\\semestr\\tik\\src\\decoder\\shannon\\files\\input.txt"))) {
            String strCurrentLine;

            strCurrentLine = bufferedReader.readLine();
            for (String s : strCurrentLine.split("\\s")) {
                chars.add(s.charAt(0));
            }
            strCurrentLine = bufferedReader.readLine();
            for (String s : strCurrentLine.replaceAll(",", ".").split("\\s")) {
                probability.add(Double.parseDouble(s));
            }
        } catch (Exception e) {
            System.out.println("Not found " + e.getMessage());
        }
        Decoder coder = new Decoder(chars, probability);
        for (Character character : coder.decrypt("000100000010000101")) {
            System.out.print(character);
        }
    }
}