package org.example.lambdas;

@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);
}
