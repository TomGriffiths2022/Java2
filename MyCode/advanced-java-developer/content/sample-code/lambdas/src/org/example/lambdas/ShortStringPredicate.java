package org.example.lambdas;

public class ShortStringPredicate implements Predicate<String> {
    @Override
    public boolean test(String string) {
        return string.length() < 5;
    }
}
