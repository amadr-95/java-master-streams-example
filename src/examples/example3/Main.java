package examples.example3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
        Given the following array of strings. String[] input = "1,1,1,1,3,4,5,6,7,7,7,8,8";
        Find the element that has the most number of occurrences
         */

        String[] input = {"1,1,1,1,3,4,5,6,7,7,7,8,8"};
        mostNumberOfOccurrencesImperative(input);
        mostNumberOfOccurrencesDeclarative(input);
    }

    private static void mostNumberOfOccurrencesImperative(String[] input) {
        Map<String, Integer> occurrencesMap = numberOfOcurrences(input);

        String maxElement = "";
        int maxOccurrences = 0;
        for (String key : occurrencesMap.keySet()) {
            int occurrences = occurrencesMap.get(key);
            if (occurrences > maxOccurrences) {
                maxElement = key;
                maxOccurrences = occurrences;
            }
        }
        System.out.println("Element with the most occurrences: " + maxElement);
    }

    private static Map<String, Integer> numberOfOcurrences(String[] input) {
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String element : input) {
            String[] elements = element.trim().split(",");
            for (String e : elements) {
                occurrencesMap.put(e, occurrencesMap.getOrDefault(e, 0) + 1);
            }
        }
        return occurrencesMap;
    }

    private static void mostNumberOfOccurrencesDeclarative(String[] input) {
        Arrays.stream(input)
                .flatMap(s -> Arrays.stream(s.trim().split(",")))
                .collect(Collectors.groupingBy(
                        number -> number,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> e.getKey() + ". Occurrences: " + e.getValue() + " times")
                .ifPresent(System.out::println);
    }

}

