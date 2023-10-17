package org.example.lambdas;

public class EvenNumberPredicate implements Predicate<Integer> {


    @Override
    public boolean test(Integer integer) {
        return integer % 2 == 0;
    }
}
