package fr.istic.vv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utils {


    public static List<String> readRomanNumeralsFromFile(String filePath) throws IOException {
        List<String> romanNumerals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String numeral = parts[1].trim();
                    romanNumerals.add(numeral);
                }
            }
        }

        return romanNumerals;
    }
}
