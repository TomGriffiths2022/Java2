package org.example.hashcodeandequals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    public void testBookEquality() {
        //test that 2 identical books are equal
        var book1 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        var book2 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        // assertEquals does this: book1.equals(book2); false
        // the default implementation of equals uses ==
        // when used with reference variables, == tests for equality of reference not content!
        assertEquals(book1, book2);

    }

    @Test
    public void testListOfBooks() {
        //test that List#contains returns true if the Book is in the list
        var book1 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        var book2 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        var books = List.of(book1);
        // internally the List class uses the Book's equals method
        assertTrue(books.contains(book2));
    }

    @Test
    public void testSetOfBooks() {
        //test that a Set will reject the adding of a duplicate Book
        var book1 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        var book2 = new Book("HelloWorld", "John Jones", "Fantasy", 1981);
        var books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        assertEquals(1, books.size());
    }
}
