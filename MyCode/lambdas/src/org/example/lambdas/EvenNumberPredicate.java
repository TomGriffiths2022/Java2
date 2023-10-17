package org.example.lambdas;

// Implementing an interface is the same as extending a class
public class EvenNumberPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer integer) {
        return integer % 2 == 0;
    }
}
