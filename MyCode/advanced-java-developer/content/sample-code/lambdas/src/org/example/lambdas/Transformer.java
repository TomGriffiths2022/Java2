package org.example.lambdas;

@FunctionalInterface
public interface Transformer<T, R> {
    R transform(T t);
}
