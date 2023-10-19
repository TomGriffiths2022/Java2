package org.example.lambdas;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class MethodReferenceTest {

    public static String getGreeting() {
        return "Hello";
    }

    public static void main(String[] args) {

        // If a method exists that might to be used as a FI impl., then coding a lambda is redundant

        Supplier<String> s1 = () -> "Hello";

        // a static method reference
        // ClassName::methodName
        Supplier<String> s2 = MethodReferenceTest::getGreeting;

        // an instance method reference
        // objectName::methodName
        var service = new GreetingService();
        Supplier<String> s3 = service::getGreeting;

        // a constuctor method reference
        // ClassName::new
        Supplier<String> s4 = String::new;

        // ---

        Consumer<String> c1 = System.out::println;
    }
}
