package org.example.oop;

/*
 * There are no interfaces at runtime.
 * Interfaces are syntactic sugar for abstract classes.
 * Each method in an interface is assumed to be public and abstract.
 */
public interface GreetingValidator {

    boolean isValid(String greeting);
}
