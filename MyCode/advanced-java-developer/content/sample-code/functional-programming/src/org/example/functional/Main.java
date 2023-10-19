package org.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        /*
         * There are many functional interfaces in the java.util.function package.
         * There are five of them that you should memorise.
         * They are: Consumer, Supplier, Predicate, Function, Runnable (in the java.lang package).
         *
         * TODO code a lambda implementation of each of the above FIs.
         */

        // the one method in Consumer accepts a T and returns nothing
        Consumer<Integer> myConsumer = number -> System.out.println(number);
        myConsumer.accept(42);

        // the one method in Supplier accepts nothing and returns a T
        Supplier<List<String>> mySupplier = () -> Arrays.asList("lewis, bob, dave");
        System.out.println(mySupplier.get());

        // the one method accepts a T and returns a boolean
        Predicate<Integer> myPredicate = number -> number > 10;
        System.out.println(myPredicate.test(42));

        // the one method accepts a T and returns an R
        Function<String, Integer> myFunction = stringToConvert -> Integer.parseInt(stringToConvert);
        System.out.println(myFunction.apply("20"));

        // the one method accepts nothing and returns nothing
        Runnable myRunnable = () -> System.out.println("Hello");
        myRunnable.run();
    }
}
