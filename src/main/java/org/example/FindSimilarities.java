package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSimilarities {
    public static int getLevenShteinDistance(String X, String Y)
    {
        int m = X.length();
        int n = Y.length();

        int[][] T = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            T[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            T[0][j] = j;
        }

        int cost;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                cost = X.charAt(i - 1) == Y.charAt(j - 1) ? 0: 1;
                T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1),
                        T[i - 1][j - 1] + cost);
            }
        }

        return T[m][n];
    }

    public static double findSimilarity(String x, String y) {
        if (x == null || y == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }

        double maxLength = Double.max(x.length(), y.length());
        if (maxLength > 0) {
            return (maxLength - getLevenShteinDistance(x, y)) / maxLength;
        }
        return 1.0;
    }
    public static List<String> getSimilarities(String[] firstArray, String[] secondArray){
        String[] biggerArray;
        String[] smallerArray;
        if(secondArray.length > firstArray.length){
            biggerArray = secondArray;
            smallerArray = firstArray;
        }else{
            biggerArray = firstArray;
            smallerArray = secondArray;
        }
        List<Double> listOfCoefficients = new ArrayList<>();
        Map<Double, String> mapOfAllSimilarities = new HashMap<>();
        List<String> listOfTheBestSimilarities = new ArrayList<>();

        for (String smallerArrayString : smallerArray) {
            for (String biggerArrayString : biggerArray) {
                double similar = findSimilarity(smallerArrayString, biggerArrayString);
                mapOfAllSimilarities.put(similar, biggerArrayString + ":" + smallerArrayString);
                listOfCoefficients.add(similar);

            }
            double maxK = listOfCoefficients.stream().max(Double::compare).get();

            listOfTheBestSimilarities.add(mapOfAllSimilarities.get(maxK));
            listOfCoefficients.clear();
        }

        int k = 0;
        for (String firstString : biggerArray) {
            for (String s : listOfTheBestSimilarities) {
                if (s.contains(firstString)) {
                    k++;
                }
            }
            if (k == 0) {
                listOfTheBestSimilarities.add(firstString + ":?");
            }
        }
        return listOfTheBestSimilarities;
    }


}
