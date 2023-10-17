package org.example.lambdas;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    @Test
    public void testFilterNumbersWithEvenNumberPredicate() {
        // The data type of numbers is inferred
        var numbers = List.of(1, 2, 3, 4, 5, 6);
        var evenNumbers = Utils.filter(numbers, new EvenNumberPredicate());
        assertEquals(3, evenNumbers.size());
        assertTrue(evenNumbers.containsAll((List.of(2, 4, 6))));
    }

    @Test
    public void testFilterWithShortStringPredicate() {
        var names = List.of("David", "Adam", "Kadmin", "Tom", "Lewis", "Arthur", "Nath");
        var shortNames = Utils.filter(names, new ShortStringPredicate());
        assertEquals(3, shortNames.size());
        assertTrue(shortNames.containsAll((List.of("Adam", "Tom", "Nath"))));
    }

    @Test
    public void testTransformWithStringToLengthTransformer() {
        var names = List.of("David", "Adam", "Kadmin", "Tom", "Lewis", "Arthur", "Nath");
        var lengthsOfNames = Utils.transform(names, new StringToLengthTransformer());
        assertEquals(5, (int) lengthsOfNames.get(0));
    }

    @Test
    public void testFilterWithAnonymousInnerClass() {
        var numbers = List.of(1, 2, 3, 4, 5, 6);
        var numbersExcludingMultiplesOfThree = Utils.filter(numbers, new Predicate<Integer>() {
            // we can provide an anonymous implementation of the interface inline
            @Override
            public boolean test(Integer integer) {
                return integer % 3 != 0;
            }
        });
        assertEquals(4, numbersExcludingMultiplesOfThree.size());
        assertTrue(numbersExcludingMultiplesOfThree.containsAll(List.of(1,2,4,5)));
    }

    // A lambda is an instance of an anonymous implementation of a functional interface
    // A functional interface is an interface with only one abstract method
    // The lambda enables us to do away with all that can be inferred - class name, method name etc.
    // Syntax rules:
    //  - The parameter types may be inferred
    //  - If the method accepts only one arg then the round brackets may be omitted
    //  - If there is only one line of code, then the curly braces may be omitted
    //  - If you omit the braces, you MUST omit the return keyword

    @Test
    public void testFilterWithLambda() {
        // replacing the new Predicate call with a lambda
        var numbers = List.of(1, 2, 3, 4, 5, 6);
        //  var numbersExcludingMultiplesOfThree = Utils.filter(numbers, (Integer integer) -> { return integer % 3 != 0; } ) ;
        var numbersExcludingMultiplesOfThree = Utils.filter(numbers, integer -> integer % 3 != 0) ;
        assertEquals(4, numbersExcludingMultiplesOfThree.size());
        assertTrue(numbersExcludingMultiplesOfThree.containsAll(List.of(1,2,4,5)));
    }
}
