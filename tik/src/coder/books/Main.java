package coder.books;

import converter.bw.Converter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Character> chars = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("C:\\Users\\Milana\\Desktop\\semestr\\tik\\src\\coder\\books\\files\\input.txt"))) {
            String strCurrentLine;
            strCurrentLine = bufferedReader.readLine();
            for (String s : strCurrentLine.split("\\s")) {
                chars.add(s.charAt(0));
            }
        } catch (Exception e) {
            System.out.println("Not found " + e.getMessage());
        }
        Converter converter = new Converter("ebaddec");

        Coder coder = new Coder(chars);
        String result = "";
        for (Integer integer : coder.encrypt(converter.getMessage())) {
            result = result.concat(integer.toString());
        }
        System.out.println(result);
        System.out.println(converter.getPosition());
    }
}
