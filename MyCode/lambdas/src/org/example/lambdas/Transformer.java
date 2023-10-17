package org.example.lambdas;

public interface Transformer<T, R> {
    R transform(T t);
}
