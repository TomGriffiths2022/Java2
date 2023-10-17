package org.example.arrayutils;

public class ArrayUtils {
    public static List<Object> toList(Object[] array) {
        var list = new LinkedList<>();
        for (var object : array) {
            list.add(object);
        }
        return list;
    }
}
