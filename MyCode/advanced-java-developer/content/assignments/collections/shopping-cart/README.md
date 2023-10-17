<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Collections Assignment

## Shopping Cart

[<< back](../../../../README.md#collections)

You're going to build a shopping cart that faciltates the adding and removing of products. The user may choose to add any quantity of any product one or more times. For example, the user might choose at first to add two apples to his cart and then, some time later, to add a further two apples. The cart, in this case, should contain four apples. What is more the user may choose to remove any quantity of any product, or to remove a product entirely. Here's a visual representation:

| Operation            | Cart      |
|----------------------|-----------|
| User adds 2 apples   | Apple x 2 |
| User adds 2 apples   | Apple x 4 |
| User removes 1 apple | Apple x 3 |
| User removes apples  | Empty     |

### Part 1

1. Create a Maven project using the quickstart archetype named `shopping-cart`.

2. In the POM configure the maven-compiler-plugin for Java 11.<details>
    <summary>Show me</summary>

    ```xml
    <properties>
      <maven.compiler.source>17</maven.compiler.source>
      <maven.compiler.target>17</maven.compiler.target>
    </properties>
    ```
</details>

3. Add to the POM a dependency for junit (org.junit.jupiter:junit-jupiter).<details>
    <summary>Show me</summary>

    ```xml
    <dependencies>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version> <!-- or whatever is the latest version -->
        <scope>test</scope>
      </dependency>
    </dependencies>
    ```
</details>

4. Add a package to the src/main/java directory named `org.example.shoppingcart`.

5. Add a class to the org.example.shoppingcart package named `Cart`.

6. Each cart is comprised of a Map of items. Why a Map? Because we've to store products (keys) and their quantities (values). We also want to be able to lookup specific products so as to change their quantities. Add a field to the Cart class to represent this attribute.<details>
    <summary>Show me</summary>

    ```java
    private Map<String, Integer> items;
    ```
</details>

7. Add a no-args constructor to the Cart class that initialises the Map.<details>
    <summary>Show me</summary>

    ```java
    public Cart() {
      items = new HashMap<>();
    }
    ```
</details>

8. Add a method to the Cart class named `add` that accepts a product and a quantity. If the given product is in the Map already then you should increment its value by the given quantity. If the given product is not in the Map then you should add it. To avoid issues with product case, you should convert it to lower case.<details>
    <summary>Show me</summary>

    ```java
    public void add(String product, int qty) {
      product = product.toLowerCase();
      if (items.containsKey(product)) {
        var currentQty = items.get(product);
        items.put(product, currentQty + qty);
      } else {
        items.put(product, qty);
      }
    }
    ```
</details>

9. Add a method to the Cart class named `remove` that accepts a product and a quantity. If the given product is in the Map already then you should decrement its value by the given quantity. If the given product is not in the Map then you should throw an exception with an appropriate message, e.g. No such product.<details>
    <summary>Show me</summary>

    ```java
    public void remove(String product, int qty) throws Exception {
      product = product.toLowerCase();
      if (items.containsKey(product)) {
        var currentQty = items.get(product);
        items.put(product, currentQty - qty);
      } else {
        throw new Exception(String.format("No such product: %s", product));
      }
    }
    ```
</details>

10. Add a method to the Cart class named `removeAll` that accepts a product. If the given product is in the Map then you should remove it. If the given product is not in the Map then you should throw an exception with an appropriate message.<details>
    <summary>Show me</summary>

    ```java
    public void removeAll(String product) throws Exception {
      product = product.toLowerCase();
      if (items.containsKey(product)) {
        items.remove(product);
      } else {
        throw new Exception(String.format("No such product: %s", product));
      }
    }
    ```
</details>

11. Add a method to the Cart class named `getProducts` that returns a Set of the Map's keys.<details>
    <summary>Show me</summary>

    ```java
    public Set<String> getProducts() {
      return items.keySet();
    }
    ```
</details>

12. Finally, add a method to the Cart class named `getItems` that returns an unmodifiable Map of items. Why unmodifiable? Because if we return a reference to the items Map then it might be changed without calling one of the Cart object's methods thus bypassing encapsulation and (potentially) breaking business rules.<details>
    <summary>Show me</summary>

    ```java
    public Map<String, Integer> getItems() {
      return Collections.unmodifiableMap(items);
    }
    ```
</details>

### Part 2

1. Add a package to the src/test/java directory named `org.example.shoppingcart`.

2. Add a class to the org.example.shoppingcart package named `CartTest`.

3. Add a field of type Cart to the CartTest class named `cart`.<details>
    <summary>Show me</summary>

    ```java
    private Cart cart;
    ```
</details>

4. Add a method to the CartTest class named `setUp` and annotate it with `@BeforeEach`. It should initialise the instance field with a new Cart object.<details>
    <summary>Show me</summary>

    ```java
    @BeforeEach
    public void setUp() {
      cart = new Cart();
    }
    ```
</details>

5. Add a method to the CartTest class named `testAdd` and annotate it with `@Test`. It should add to the Cart object three products and verify that the cart has the expected items.<details>
    <summary>Show me</summary>

    ```java
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
    ```
</details>

6. Add a method to the CartTest class named `testAddSameProductMultipleTimes` and annotate it with `@Test`. It should add to the Cart object the same product two or more times and verify that the cart has the expected items.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testAddSameProductMultipleTimes() {
      cart.add("apple", 1);
      cart.add("apple", 2);
      assertEquals(1, cart.getProducts().size());
      assertTrue(cart.getProducts().contains("apple"));
      assertEquals(3, cart.getItems().get("apple"));
    }
    ```
</details>

7. Add a method to the CartTest class named `testRemove` and annotate it with `@Test`. It should add to the Cart object two or more of a given product and then remove one. It should then verify that the cart has the expected items.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testRemove() throws Exception {
      cart.add("apple", 2);
      cart.remove("apple", 1);
      assertEquals(1, cart.getProducts().size());
      assertTrue(cart.getProducts().contains("apple"));
      assertEquals(1, cart.getItems().get("apple"));
    }
    ```
</details>

8. Add a method to the CartTest class named `testRemoveAll` and annotate it with `@Test`. It should add to the Cart object one or more of a given product and then remove them all. It should then verify that the cart does not have the given product.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testRemoveAll() throws Exception {
      cart.add("apple", 2);
      cart.removeAll("apple");
      assertEquals(0, cart.getProducts().size());
    }
    ```
  </details>

9. Execute Maven's test phase.

   `$> mvn test`<br />

   Note that to run Maven's test phase for JUnit 5 tests you need version >= 2.21.X of the Maven Surefire plugin. If necessary, add the following to the POM after the dependencies section:

   ```xml
   <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.22.2</version>
            </plugin>
        </plugins>
    </build>
   ```

### Optional Extra

Think about the other tests that your CartTest class might/should have and add them. If you can't think of any more then ask your trainer for help. Then use Maven to install your app into the local Maven repo for use by other projects.
 
[<< back](../../../../README.md#collections)
