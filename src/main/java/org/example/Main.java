package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser("src/main/resources/input.txt");
        List<String> similarities = FindSimilarities.getSimilarities(parser.getFirstArray(), parser.getSecondArray());
        Parser.printToFile(similarities);
    }
}
