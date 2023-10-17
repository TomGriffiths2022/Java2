<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Functional Programming Assignment

## Filter Method

[<< back](../../../../README.md#functional-programming)

You're going to apply functional techniques to code a method to filter a List (of numbers initially). The method will be pure insofar as its output will be dependent only on its input and it will not have any side-effects. It will be like a higher order function insofar as it will accept a Predicate that encapsulates the filter logic. It will treat the input List as if it were immutable and so perform a transformation (create and return a new List based on the input one).

## Part 1

1. Create a new Maven project using the quickstart archetype named `filter-method`.

2. In the POM configure the maven-compiler-plugin for Java 17.<details>
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

4. Add a package to the src/main/java directory named `org.example.listutils`.

5. Add a class to the org.example.listutils package named `ListUtils`.

6. Add a static method to the ListUtils class named `filter` that accepts a List&lt;Integer&gt; and a Predicate&lt;Integer&gt;, and returns a List&lt;Integer&gt;. In it you should iterate over the input List and apply the Predicate's test method to each element. Those elements for which the method returns true should be added to a new List which should ultimately be returned.<details>
    <summary>Show me</summary>

    ```java
    public static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
        var filteredList = new ArrayList<Integer>();
        for (var element : list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }
    ```
</details>

## Part 2

1. Add a package to the src/main/test directory named `org.example.listutils`.

2. Add a class to the org.example.listutils package (in src/main/test) named `ListUtilsTest`.

3. Add a method to the ListUtilsTest class named `testFilterWithBigNumberPredicate` and annotate it with `@Test`. It should initialise a List of integers containing the numbers 1, 10, 100, 1000, 10_000, and 100_000 and then pass it to the ListUtils::filter method alongside a lambda Predicate implementation that returns true if the given number is greater than 100. Finally, it should assert that the newly created List contains only the numbers 1000, 10_000, and 100_000.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFilterWithBigNumberPredicate() {
        var list = List.of(1, 10, 100, 1000, 10_000, 100_000);
        var filteredList = ListUtils.filter(list, n -> n > 100);
        assertEquals(3, filteredList.size());
        assertTrue(filteredList.containsAll(List.of(1000, 10_000, 100_000)));
    }
    ```
</details>

4. Add a method to the ListUtilsTest class named `testFilterWithOddNumberPredicate` and annotate it with `@Test`. It should initialise a List of integers containing the numbers 1 thru 5 and then pass it to the ListUtils::filter method alongside a lambda Predicate implementation that returns true if the given number is odd. Finally, it should assert that the newly created List contains only the numbers 1, 3, and 5.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFilterWithOddNumberPredicate() {
        var list = List.of(1, 2, 3, 4, 5);
        var filteredList = ListUtils.filter(list, n -> n % 2 != 0);
        assertEquals(3, filteredList.size());
        assertTrue(filteredList.containsAll(List.of(1, 3, 5)));
    }
    ```
</details>

5. Add a method to the ListUtilsTest class named `testFilterWithBigOddNumberPredicate` and annotate it with `@Test`. It should initialise a List of integers containing the numbers 100, 101, 102, 103, 104, and 105 and then pass it to the ListUtils::filter method alongside a lambda Predicate implementation that returns true if the given number is greater than 100 and is odd (do this by composing two existing Predicate lambdas). Finally, it should assert that the newly created List contains only the numbers 101, 103, and 105.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFilterWithBigOddNumberPredicate() {
        var list = List.of(100, 101, 102, 103, 104, 105);
        Predicate<Integer> bigNumberPredicate = n -> n > 100;
        Predicate<Integer> oddNumberPredicate = n -> n % 2 != 0;
        List<Integer> filteredList = ListUtils.filter(list, bigNumberPredicate.and(oddNumberPredicate));
        assertEquals(3, filteredList.size());
        assertTrue(filteredList.containsAll(List.of(101, 103, 105)));
    }
    ```
</details>

## Part 3

1. Modify the ListUtils::filter method such that it may be used to filter Lists of anything, not just integers. Note that this will require you to make the method generic. You may want to refer back to the code you produced during the section on generics.<details>
    <summary>Show me</summary>

    ```java
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        var filteredList = new ArrayList<T>();
        for (var element : list) {
            if (predicate.test(element)) {
                filteredList.add(element);
            }
        }
        return filteredList;
    }
    ```
</details>

2. Add a method to the ListUtilsTest class named `testFilterWithNonConnectivePredicate` and annotate it with `@Test`. It should initialise a List of Strings comprising the words from the following quote `Integrity without knowledge is weak and useless, and knowledge without integrity is dangerous and dreadful.` It should pass the list to the ListUtils::filter method alongside a lambda Predicate implementation that returns true if the given word is not one of `is` or `and`. Finally, it should assert that the newly created List contains only the non-connective words.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFilterWithNonConnectivePredicate() {
        var quote = "Integrity without knowledge is weak and useless, " +
            "and knowledge without integrity is dangerous and dreadful.";
        var connectives = List.of("is", "and");
        var list = Arrays.asList(quote.split("[\\s,]+"));
        var filteredList = ListUtils.filter(list, word -> !connectives.contains(word));
        assertEquals(10, filteredList.size());
        assertFalse(filteredList.containsAll(List.of("is", "and")));
    }
    ```
</details>

[<< back](../../../../README.md#functional-programming)