package org.example.lambdas;

import java.util.ArrayList;
import java.util.List;
public class Utils {

    // Using T (any type parameter) makes the method and List generic
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        // the method should be transformative, not mutative
        // that is the List passed in should NOT be modified (create and return a new List)
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T, R> List<R> transform(List<T> list, Transformer<T, R> transformer) {
        // the method should be transformative, not mutative
        // that is the List passed in should NOT be modified (create and return a new List)
        // New list needs to be of type R
        List<R> newList = new ArrayList<>();
        for (T t : list) {
                newList.add(transformer.transform(t));
        }
        return newList;
    }
}
