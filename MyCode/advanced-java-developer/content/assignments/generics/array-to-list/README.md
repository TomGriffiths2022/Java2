<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Generics Assignment

## Array to List

[<< back](../../../../README.md#generics)

You're going to code a static method that accepts an array and transforms it into a list. This is not as easy as it might sound, particularly if we're to avoid `List<Object>` and `List<?>`.

### Part 1

1. Create a new Maven project using the quickstart archetype named `array-to-list`.

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

4. Add a package to the src/main/java directory named `org.example.arrayutils`.

5. Add a class to the org.example.arrayutils package named `ArrayUtils`.

6. Add a static method to the ArrayUtils class named `toList` that accepts an array of Objects. It should copy each of the array's elements into a List and return it.<details>
    <summary>Show me</summary>

    ```java
    public static List<Object> toList(Object[] array) {
      var list = new LinkedList<>();
      for (var object : array) {
        list.add(object);
      }
      return list;
    }
    ```
</details>

7. Add a package to the src/test/java directory named `org.example.arrayutils`. 

8. Add a class to the org.example.arrayutils package named `ArrayUtilsTest`.

9. Add a method to the ArrayUtilsTest class named `testToList` and annotate it with `@Test`. In it, call the ArrayUtils' toList method passing an array of Strings and verify that the return value is as you'd expect it to be.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testToList() {
      var list = ArrayUtils.toList(new String[] {"a", "b", "c"});
      assertTrue(list.contains("a"));
      assertTrue(list.contains("b"));
      assertTrue(list.contains("c"));
      assertEquals(3, list.size());
    }
    ```
</details>

Did you try to assign the return value to `List<String>`? It's reasonable to expect that to work, but it doesn't because a List of Strings is not (in the polymorphic sense) a List of Objects. Consequently we have to assign the return value to `List<Object>`. But that's no good either - now we have to cast each element in the list to do any String-specific stuff! Let's make the method generic.

### Part 2

1. Add a type parameter (`<T>`) to the toList method before the return type, then replace occurrences of Object with T. The type T will be determined when the method is called so that if an array of Strings is passed to the toList method, then T will become String.<details>
    <summary>Show me</summary>

    ```java
    public static <T> List<T> toList(T[] array) {
      var list = new LinkedList<T>();
      for (var object : array) {
        list.add(object);
      }
      return list;
    }
    ```
</details> 

2. Now check your test method to verify that the variable to which you assign the return value is of type `List<String>`, and re-run the test.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testToList() {
      var list = ArrayUtils.toList(new String[] {"a", "b", "c"}); // <-- return value is assigned to List<String>
      assertTrue(list.contains("a"));
      assertTrue(list.contains("b"));
      assertTrue(list.contains("c"));
      assertEquals(3, list.size());
    }
    ```
</details>

The toList method can now be used to convert an array of any type to a List of that same type. We started by exploiting polymorphism to return a List of Objects but that, consequently, requires casting and you should avoid that wherever possible. You probably won't have much cause to write your own generic methods but you will have to use existing ones. 

### Optional Extra

Use Maven to install your app into the local Maven repo for use by other projects.

[<< back](../../../../README.md#generics)