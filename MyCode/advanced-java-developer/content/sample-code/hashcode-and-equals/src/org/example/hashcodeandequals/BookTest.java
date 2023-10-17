package org.example.hashcodeandequals;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookTest {

    @Test
    public void testBookEquality() {
        // TODO test that two identical Books are equal
        var book1 = new Book("My Book", "Stuart", "Fiction", 2023);
        var book2 = new Book("My Book", "Stuart", "Fiction", 2023);
        // assertEquals does this: book1.equals(book2);
        // the default implementation of equals uses ==
        // when used with ref variables == tests for equality of ref, not content!
        assertEquals(book1, book2);
    }

    @Test
    public void testListOfBooks() {
        // TODO test that List#contains returns true if the Book is in the List
        var book1 = new Book("My Book", "Stuart", "Fiction", 2023);
        var book2 = new Book("My Book", "Stuart", "Fiction", 2023);
        var books = List.of(book1);
        // internally the List class uses the Book's equals method
        assertTrue(books.contains(book2));
    }

    @Test
    public void testSetOfBooks() {
        // TODO test that a Set will reject the adding of a duplicate Book
        var book1 = new Book("My Book", "Stuart", "Fiction", 2023);
        var book2 = new Book("My Book", "Stuart", "Fiction", 2023);
        var books = new HashSet<>();
        books.add(book1);
        books.add(book2);
        assertEquals(1, books.size());
    }
}
