package org.example.oop;

public class GreetingService {

    private String greeting;
    private GreetingValidator validator;

    public GreetingService(GreetingValidator validator) {
        this.validator = validator;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        if (validator.isValid(greeting)) {
            this.greeting = greeting;
        }
    }
}
