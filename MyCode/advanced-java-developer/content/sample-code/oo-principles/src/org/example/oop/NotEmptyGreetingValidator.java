package org.example.oop;

public class NotEmptyGreetingValidator implements GreetingValidator {

    @Override
    public boolean isValid(String greeting) {
        return !greeting.isBlank();
    }
}
