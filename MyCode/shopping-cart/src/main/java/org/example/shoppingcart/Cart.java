package org.example.shoppingcart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {

    private Map<String, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void add(String product, int quantity) {
        product = product.toLowerCase();
        if (items.containsKey(product)) {
            var currentQuantity = items.get(product);
            items.put(product, currentQuantity + quantity);
        } else {
            items.put(product, quantity);
        }
    }

    public void remove(String product, int quantity) throws Exception {
        product = product.toLowerCase();
        if (items.containsKey(product)) {
            var currentQuantity = items.get(product);
            items.put(product, currentQuantity - quantity);
        } else {
            throw new Exception(String.format("No such product: %s", product));
        }
    }

    public void removeAll(String product) throws Exception {
        product = product.toLowerCase();
        if (items.containsKey(product)) {
            items.remove(product);
        } else {
            throw new Exception(String.format("No such product: %s", product));
        }
    }

    public Set<String> getProducts() {
        return items.keySet();
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

}
