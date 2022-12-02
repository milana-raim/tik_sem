package converter.bw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converter {
    private final String message;
    private final Integer position;

    public Converter(String message) {
        String str = message;
        List<String> matrix = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            matrix.add(str);
            str = str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
        }
        Collections.sort(matrix);
        int startStrNumber = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < matrix.size(); i++) {
            ans.append(matrix.get(i).charAt(message.length() - 1));
            if (message.equals(matrix.get(i))) {
                startStrNumber = i;
            }
        }
        this.message = ans.toString();
        this.position = startStrNumber;
    }

    public String getMessage() {
        return message;
    }

    public Integer getPosition() {
        return position;
    }
}
