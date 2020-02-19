package br.com.codenation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StatisticUtil {

    public static int average(int[] elements) {
        int sum = 0;
        for (int e : elements) sum += e;
        return sum / elements.length;
    }

    public static int mode(int[] elements) {
        return convertIntArrayToList(elements)
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(IllegalArgumentException::new);
    }

    public static int median(int[] elements) {
        List<Integer> list = convertIntArrayToList(elements);

        int middleIndex = (0 + list.size() - 1) / 2; // (first index + last index) / 2

        if (list.size() % 2 == 0) {
            Integer a = list.get(middleIndex);
            Integer b = list.get(middleIndex + 1);
            return (a + b) / 2;
        } else {
            return list.get(middleIndex);
        }
    }

    private static List<Integer> convertIntArrayToList(int[] arr) {
        return Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());
    }
}