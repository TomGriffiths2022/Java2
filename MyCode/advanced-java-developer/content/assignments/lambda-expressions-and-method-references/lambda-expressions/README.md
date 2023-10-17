<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Lambda Expressions and Method References Assignment

## Lambda Expressions

[<< back](../../../../README.md#lambda-expressions-and-method-references)

A lambda expression is *not* an anonymous method. It is an instance of an anonymous implementation of a functional interface (an interface with only one abstract method). This assignment is intended to make you able to take a functional interface and to create an implementation of it in the form of a lambda expression. Whilst it may seem academic at first the ability to construct lambda expressions has implications for functional programming and streams in particular.

## Part 1

1. Create a new Maven project using the quickstart archetype named `lambda-expressions`.

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

4. Add a package to the src/main/java directory named `org.example.lambdas.interfaces`.

5. Add an interface to the interfaces package named `StringTester`. It should comprise one abstract method named `test` that accepts a String and returns a boolean.<details>
    <summary>Show me</summary>

    ```java
    public interface StringTester {
      boolean test(String s);
    }
    ```
</details>

6. Add a package to the src/test/java directory named `org.example.lambdas.interfaces`.

7. Add a class to the interfaces package (in src/test/java) named `InterfaceImplementationsTest`.

8. Add a method to the InterfaceImplementationsTest class named `testLongStringTester` and annotate it with `@Test`. It should create a lambda implementation of the StringTester interface whose one method returns true if the given String's length is 10 or more. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testLongStringTester() {
        StringTester longStringTester = s -> s.length() >= 10;
        assertTrue(longStringTester.test("Hello world"));
    }
    ```
</details>

9. Add a method to the InterfaceImplementationsTest class named `testAlphaCharsAndOrWhitespaceStringTester` and annotate it with `@Test`. It should create a lambda implementation of the StringTester interface whose one method returns true if the given String contains alpha characters and/or whitespace only. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testAlphaCharsAndOrWhitespaceOnlyStringTester() {
        StringTester alphaCharsAndOrWhitespaceOnlyStringTester = s -> s.matches("[\\w\\s]+");
        assertTrue(alphaCharsAndOrWhitespaceOnlyStringTester.test("Hello world"));
    }
    ```
</details>

## Part 2

1. Add an interface to the interfaces package (in src/main/java) named `StringTransformer`. It should comprise one abstract method named `transform` that accepts a String and returns a String.<details>
    <summary>Show me</summary>

    ```java
    public interface StringTransformer {
      String transform(String s);
    }
    ```
</details>

2. Add a method to the InterfaceImplementationsTest class named `testUpperCaseStringTransformer` and annotate it with `@Test`. It should create a lambda implementation of the StringTransformer interface whose one method returns an upper case version of the given String. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testUpperCaseStringTransformer() {
        StringTransformer upperCaseStringTransformer = s -> s.toUpperCase();
        assertEquals("HELLO WORLD", upperCaseStringTransformer.transform("Hello world"));
    }
    ```
</details>

3. Add a method to the InterfaceImplementationsTest class named `testFirstWordStringTransformer` and annotate it with `@Test`. It should create a lambda implementation of the StringTransformer interface whose one method returns the first word of the given String. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFirstWordStringTransformer() {
        StringTransformer firstWordStringTransformer = s -> s.substring(0, s.indexOf(' '));
        assertEquals("Hello", firstWordStringTransformer.transform("Hello world"));
    }
    ```
</details>

4. Add a method to the InterfaceImplementationsTest class named `testEncryptedStringTransformer` and annotate it with `@Test`. It should create a lambda implementation of the StringTransformer interface whose one method returns an encrypted version of the given String (by adding 1 to each character). It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testEncryptedStringTransformer() {
        StringTransformer encryptedStringTransformer = s -> {
            var originalChars = s.toCharArray();
            var encryptedChars = new char[s.length()];
            for (var i = 0; i < s.length(); i++) {
                encryptedChars[i] = (char) (originalChars[i] + 1);
            }
            return new String(encryptedChars);
        };
        assertEquals("Ifmmp!xpsme", encryptedStringTransformer.transform("Hello world"));
    }
    ```
</details>

## Part 3

1. Add a generic interface to the interfaces package (in src/main/java) named `GenericTransformer`. It should comprise one abstract method named `transform` that accepts a T and returns an R.<details>
    <summary>Show me</summary>

    ```java
    public interface GenericTransformer<T, R> {
      R transform(T t);
    }
    ```
</details>

2. Add a method to the InterfaceImplementationsTest class named `testStringToLengthTransformer` and annotate it with `@Test`. It should create a lambda implementation of the GenericTransformer interface whose one method returns the length of the given String. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testStringToLengthTransformer() {
        GenericTransformer<String, Integer> stringToLengthTransformer = s -> s.length();
        assertEquals(11, (int) stringToLengthTransformer.transform("Hello world"));
    }
    ```
</details>

3. Add a method to the InterfaceImplementationsTest class named `testStringToFirstCharTransformer` and annotate it with `@Test`. It should create a lambda implementation of the GenericTransformer interface whose one method returns the first character of the given String. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testStringToFirstCharTransformer() {
        GenericTransformer<String, Character> stringToFirstCharTransformer = s -> s.charAt(0);
        assertEquals('H', (char) stringToFirstCharTransformer.transform("Hello world"));
    }
    ```
</details>

4. Add a package to the src/main/java directory named `org.example.lambdas.models`.

5. Add a class to the models package named `Person` with first name, last name, DOB, and postcode fields. It should have an all-args constructor + setters and getters.

6. Add a method to the InterfaceImplementationsTest class named `testPersonToFirstNameTransformer` and annotate it with `@Test`. It should create a lambda implementation of the GenericTransformer interface whose one method returns the first name of the given Person. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testPersonToFirstNameTransformer() {
        var dob = LocalDate.of(1926, 4, 21);
        var theQueen = new Person("Elizabeth", "Windsor", dob, "SL41QF");
        GenericTransformer<Person, String> personToFirstNameTransformer = p -> p.getFirstName();
        assertEquals("Elizabeth", personToFirstNameTransformer.transform(theQueen));
    }
    ```
</details>

7. Add a method to the InterfaceImplementationsTest class named `testPersonToFullNameTransformer` and annotate it with `@Test`. It should create a lambda implementation of the GenericTransformer interface whose one method returns the full name of the given Person. It should also assert that the implementation behaves as expected.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testPersonToFullNameTransformer() {
        var dob = LocalDate.of(1926, 4, 21);
        var theQueen = new Person("Elizabeth", "Windsor", dob, "SL41QF");
        GenericTransformer<Person, String> personToFullNameTransformer =
                p -> String.format("%s %s", p.getFirstName(), p.getLastName());
        assertEquals("Elizabeth Windsor", personToFullNameTransformer.transform(theQueen));
    }
    ```
</details>
 
[<< back](../../../../README.md#lambda-expressions-and-method-references)
