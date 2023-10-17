package org.example.oop;

public class Main {

    public static void main(String[] args) {
        // var validator = new GreetingValidator();
        var validator = new NotEmptyGreetingValidator();
        var service = new GreetingService(validator);
        service.setGreeting("Hello");
    }
}
