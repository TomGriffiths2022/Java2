package org.example.lambdas;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        // the method should be transformative, not mutative
        // that is the List passed in should NOT be modified!
        List<T> newList = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    // TODO: have a go at coding the Transformer interface and an implementation of it
    public static <T, R> List<R> transform(List<T> list, Transformer<T, R> transformer) {
        // the method should be transformative, not mutative
        // that is the List passed in should NOT be modified!
        List<R> newList = new ArrayList<>();
        for (T t : list) {
            newList.add(transformer.transform(t));
        }
        return newList;
    }
}
