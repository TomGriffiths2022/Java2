package org.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        // There are many functional interfaces in the java.util.function package
        // There are five of them that you should memorise:
        // They are: Consumer, Supplier, Predicate, Function, Runnable (in the java.lang package)

        // TODO code a lambda implementation of each of the above functional interfaces.

        // Consumer<Integer> myConsumer = new MyConsumer();  this is what the below is doing without having to write the class
        Consumer<Integer> myConsumer = number -> System.out.println(number);
        myConsumer.accept(42);

        Supplier<List<String>> mySupplier = () -> Arrays.asList("lewis, bob, dave");
        System.out.println(mySupplier.get());

        Predicate<Integer> myPredicate = number -> number > 10;
        System.out.println(myPredicate.test(42));

        Function<String, Integer> myFunction = (String stringToConvert) -> Integer.parseInt(stringToConvert);
        System.out.println(myFunction.apply("20"));
    }
}
