package org.example.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericsTest {


    @Test
    public void testMyList() {
        var myList = new MyList<String>(); // String will replace T
        myList.add("Hello");
        var firstElement = myList.get(0);
        assertEquals("Hello", firstElement);
    }

    @Test
    public void testEcho() {
        // The type of T may be specified explicitly (e.g. Utils<String>) but can be inferred from the argument type
        var result = Utils.echo("Hello"); // String will replace T
    }

}
