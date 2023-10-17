package org.example.lambdas;
// Overriding the Predicate data type on instantiation
public class ShortStringPredicate implements Predicate<String> {

    @Override
    public boolean test(String string) {
        return string.length() < 5;
    }
}
