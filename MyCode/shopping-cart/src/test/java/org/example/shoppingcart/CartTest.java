package org.example.shoppingcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest {

    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
    }

    @Test
    public void testAdd() {
        cart.add("apple", 1);
        cart.add("banana", 2);
        cart.add("carrot", 3);
        assertEquals(3, cart.getProducts().size());
        assertTrue(cart.getProducts().containsAll(List.of("apple", "banana", "carrot")));
        assertEquals(1, cart.getItems().get("apple"));
        assertEquals(2, cart.getItems().get("banana"));
        assertEquals(3, cart.getItems().get("carrot"));
    }

    @Test
    public void testAddSameProductMultipleTimes() {
        cart.add("apple", 1);
        cart.add("apple", 2);
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().contains("apple"));
        assertEquals(3, cart.getItems().get("apple"));
    }

    @Test
    public void testRemove() throws Exception {
        cart.add("apple", 2);
        cart.remove("apple", 1);
        assertEquals(1, cart.getProducts().size());
        assertTrue(cart.getProducts().contains("apple"));
        assertEquals(1, cart.getItems().get("apple"));
    }

    @Test
    public void testRemoveAll() throws Exception {
        cart.add("apple", 2);
        cart.removeAll("apple");
        assertEquals(0, cart.getProducts().size());
    }
}
