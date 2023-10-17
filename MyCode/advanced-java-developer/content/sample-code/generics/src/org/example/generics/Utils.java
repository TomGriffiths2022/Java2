package org.example.generics;

public class Utils {

    // the <T> after the mods and before the return type signals a generic method
    // the T will be replaced by a real data type at call time
    public static <T> T echo(T t) {
        return t;
    }

    // T extends Number means T can be Number or any subclass of Number
    public static <T extends Number> T square(T number) {
        // TODO
        return number;
    }
}
