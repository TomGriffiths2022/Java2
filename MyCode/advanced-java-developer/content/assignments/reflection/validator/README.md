<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Reflection (and Annotations) Assignment

## Validator

[<< back](../../../../README.md#reflection)

You're going to build and test a validator library that utilises both reflection and annotations. The library will provide for the validation of POJOs via metadata thereby eliminating the need for validation logic inside of said POJOs. It will also minimise code duplication.

For example, an instance of some class annotated like this: ```@NotBlank private String name;``` shall be validated like this: ```var errors = validator.validate(instance);``` without the need for any validation logic inside of the given class.

### Part 1

1. Create a new Maven project using the quickstart archetype named `validator`.

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

4. Add a package to the src/main/java directory named `org.example.validator`.

5. Add an annotation to the org.example.validator package named `NotBlank`. It should target fields and be retained at runtime. It will be used to validate that the values assigned to the fields on which it is applied are not blank (null, empty String, or String comprising whitespace only).<details>
    <summary>Show me</summary>
    
    ```java
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NotBlank {
    }
    ```
</details>

6. Add a class to the org.example.validator package named `Validator`.

7. Add a private method to the Validator class named `addError` that accepts a Map of errors, a field name, and an error message. Note that the Map key should be a String (field name) and the value should be a List&lt;String&gt; (error messages). If the errors Map contains the given field name key then the error message should be added to the associated List of error messages. If the errors Map does not contain the given field name key then a new key value pair should be added to the errors Map.<details>
    <summary>Show me</summary>

    ```java
    private void addError(Map<String, List<String>> errors, String fieldName, String errorMessage) {
        if (errors.containsKey(fieldName)) {
            errors.get(fieldName).add(errorMessage);
        } else {
            var errorMessages = new ArrayList<String>();
            errorMessages.add(errorMessage);
            errors.put(fieldName, errorMessages);
        }
    }
    ```
</details>

8. Add a public method to the Validator class named `validate` that accepts the instance to be validated (which should be of type Object) and its associated class. It should initialise an empty Map of errors. For each of the declared fields it should obtain the field name, value, and annotations. If @NotBlank is among the annotations and the field's value is blank then the addError method should be called. The method should return the Map of errors.<details>
    <summary>Show me</summary>

    ```java
    public Map<String, List<String>> validate(Object object, Class clazz) throws IllegalAccessException {
        var errors = new HashMap<String, List<String>>();
        for (var field : clazz.getDeclaredFields()) {
            field.setAccessible(true); // <!-- necessary to read private members
            var fieldName = field.getName();
            var fieldValue = field.get(object);
            var fieldAnnotations = field.getAnnotations();
            for (var annotation : fieldAnnotations) {
                if (annotation.annotationType() == NotBlank.class) {
                    if (fieldValue == null || ((String) fieldValue).isBlank()) {
                        addError(errors, fieldName, fieldName + " is blank");
                    }
                }
            }
        }
        return errors;
    }
    ```
</details>

### Part 2

1. Add a package to the src/test/java directory named `org.example.validator`.

2. Add a POJO class to the org.example.validator package (inside src/test/java) named `Person` with name and age fields, constructor, and getters and setters. Annotate the name field with @NotBlank.<details>
    <summary>Show me</summary>

    ```java
    public class Person {

        @NotBlank
        private String name;

        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // getters and setters
    }
    ```
</details>

3. Add a class to the org.example.validator package (inside src/test/java) named `ValidatorTest`.

4. Add a method to the ValidatorTest class named `testValidateWithNotBlank` and annotate it with `@Test`. It should intialise a Validator and call its validate method passing in a Person instance with a blank name. It should then assert that the Map returned contains one key value pair where the key is "name" and the value is a List comprising one error message.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testValidateWithNotBlank() throws IllegalAccessException {
        var validator = new Validator();
        var errors = validator.validate(new Person(null, 30), Person.class);
        assertEquals(1, errors.size());
        assertEquals(1, errors.get("name").size());
        assertTrue(errors.get("name").contains("name is blank"));
    }
    ```
</details>

### Part 3

1. Add an annotation to the org.example.validator package (inside src/main/java) named `InRange`. It should target fields and be retained at runtime. It will be used to validate that the values assigned to the fields on which it is applied are within a range, e.g. 0-100. Note that this will require the annotation to have attributes, e.g. min and max.<details>
    <summary>Show me</summary>
    
    ```java
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InRange {

        int min();

        int max();
    }
    ```
</details>

2. Modify the Validator::validate method such that it checks for the precense of @InRange among each of the field's annotations and validates the field's value accordingly. Note that in order to access the annotation's attributes you will first have to cast the annotation to type InRange.<details>
    <summary>Show me</summary>

    ```java
    // ...other method code
    if (annotation.annotationType() == InRange.class) {
        var inRangeAnnotation = (InRange) annotation;
        var min = inRangeAnnotation.min();
        var max = inRangeAnnotation.max();
        var doubleFieldValue = Double.parseDouble(fieldValue.toString());
        if (doubleFieldValue < min || doubleFieldValue > max) {
            addError(errors, fieldName, fieldName + " is out of range");
        }
    }
    // ...other method code
    ```
</details>

3. Add the @InRange annotation to the age field on the Person class inside src/main/test. Set the minimum age to 0 and the maximum age to 130.<details>
    <summary>Show me</summary>

    ```java
    @InRange(min = 0, max = 130)
    private int age;
    ```
</details>

4. Add a method to the ValidatorTest class named `testValidateWithInRange` and annotate it with `@Test`. It should intialise a Validator and call its validate method passing in a Person instance with an out-of-range age. It should then assert that the Map returned contains one key value pair where the key "age" and the value is a List comprising one error message.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testValidateWithInRange() throws IllegalAccessException {
        var validator = new Validator();
        var errors = validator.validate(new Person("Dave", -1), Person.class);
        assertEquals(1, errors.size());
        assertEquals(1, errors.get("age").size());
        assertTrue(errors.get("age").contains("age is out of range"));
    }
    ```
</details>

5. Add a method to the ValidatorTest class named `testValidateWithNotBlankAndInRange` and annotate it with `@Test`. It should initialise a Validator and call its validate method passing in a Person instance with a blank name and out-of-range age. It should then assert that the Map returned contains two key value pairs.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testValidateWithNotBlankAndInRange() throws IllegalAccessException {
        var validator = new Validator();
        var errors = validator.validate(new Person(null, -1), Person.class);
        assertEquals(2, errors.size());
        assertEquals(1, errors.get("name").size());
        assertEquals(1, errors.get("age").size());
        assertTrue(errors.get("name").contains("name is blank"));
        assertTrue(errors.get("age").contains("age is out of range"));
    }
    ```
</details>

We've thus far tested only the unhappy paths. You might like to write some more tests to ensure the validator works as expected when used to validate valid/partially valid objects.

[<< back](../../../../README.md#reflection)