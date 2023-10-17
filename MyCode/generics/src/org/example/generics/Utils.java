package org.example.generics;

public class Utils {
    // The <T> after the modifier and before the return type signals a generic method
    // The T will be replaced by a real data type at call time
    public static <T> T echo(T t) {
        return t;
    }
}
