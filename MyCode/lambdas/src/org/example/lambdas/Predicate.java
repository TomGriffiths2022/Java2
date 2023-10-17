package org.example.lambdas;

// Interfaces are a layer between 2 pieces of code
// It can only have abstract methods, must be overridden by the subclass
// Methods inside an interface are public and abstract by default
public interface Predicate<T> {

    boolean test(T t);
}
