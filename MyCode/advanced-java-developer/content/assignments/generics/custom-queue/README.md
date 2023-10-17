<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Generics Assignment

## Custom Queue

[<< back](../../../../README.md#generics)

You're going to build a custom queue with add and remove behaviour. Items will be added at the back of the queue and removed from the front. If an attempt is made to add when the queue is full it will throw an exception, likewise if an attempt is made to remove when the queue is empty. Initially your Queue class will be non-generic so that you understand the benefits of making it so.

### Part 1

1. Create a new Maven project using the quickstart archetype named `custom-queue`.

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

4. Add a package to the src/main/java directory named `org.example.customqueue`.

5. Add a class to the org.example.customqueue package named `Queue`.

6. Each queue is comprised of an array of items, a capacity, and a number of items currently in the queue. Add fields to the Queue class to represent these attributes. Note that your array of items should be of type Object[]. That way your Queue objects can store anything.<details>
    <summary>Show me</summary>

    ```java
    private Object[] items;
    private int capacity;
    private int numItems;
    ```
</details>

7. Add a constructor to the Queue class that accepts a capacity and initialises all fields appropriately.<details>
    <summary>Show me</summary>

    ```java
    public Queue(int capacity) {
      this.items = new Object[capacity];
      this.capacity = capacity;
      this.numItems = 0;
    }
    ```
</details>

8. Add a method to the Queue class named `add` that accepts an item (of type Object). If the number of items in the queue is less than its capacity then add the item in the next available slot and increment the number of items in the queue. If the number of items in the queue is not less than its capacity then throw an exception with an appropriate message.<details>
    <summary>Show me</summary>

    ```java
    public void add(Object item) throws Exception {
      if (numItems < capacity) {
        items[numItems++] = item;
      } else {
        throw new Exception("The queue is full");
      }
    }
    ```
</details>

9. Add a method to the Queue class named `remove` that accepts no arguments. If the number of items in the queue is greater than zero then get the first item, move all the remaining ones to index - 1, decrement the number of items in the queue, and return the first item. If the number of items in the queue is zero then throw an exception with an appropriate message.<details>
    <summary>Show me</summary>

    ```java
    public Object remove() throws Exception {
      if (numItems > 0) {
        var firstItem = items[0];
        for (var i = 1; i < numItems; i++) {
          items[i - 1] = items[i];
        }
        numItems--;
        return firstItem;
      } else {
        throw new Exception("There are no items in the queue");
      }
    }
    ```
</details>

10. Add to the Queue class getter methods for both capacity and the number of items in the queue.<details>#
    <summary>Show me</summary>

    ```java
    public int getCapacity() {
      return capacity;
    }

    public int getNumItems() {
      return numItems;
    }
    ```
</detais>

### Part 2

1. Add a package to the src/test/java directory named `org.example.customqueue`.

2. Add a class to the org.example.customqueue package named `QueueTest`.

3. Add a field of type Queue to the QueueTest class named `queue`.<details>
    <summary>Show me</summary>

    ```java
    private Queue queue;
    ```
</details>

4. Add a method to the QueueTest class named setUp and annotate it with `@BeforeEach`. It should initialise the instance field with a new Queue object with a capacity of `3`.<details>
    <summary>Show me</summary>

    ```java
    @BeforeEach
    public void setUp() {
      queue = new Queue(3);
    }
    ```
</details>

5. Add a method to the QueueTest class named `testAdd` and annotate it with `@Test`. It should add to the Queue object a LocalDate, a String, and a number. We can do this because the Queue's add method accepts an argument of type Object, and all types ultimately inherit from Object. That is, a LocalDate is an Object, as is a String, as is an Integer. Hello polymorphism!<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testAdd() throws Exception {
      queue.add(LocalDate.now());
      queue.add("Hello");
      queue.add(42);
    }
    ```
</details>

All good, right? Well no. Adding stuff to the Queue is okay, but what about getting stuff out. The Queue's remove method returns an Object, and so each element must be cast before we can do anything specific with it. If we were to iterate over the queue we'd have to do something like this:

```java
for (var i = 0; i < queue.getNumItems(); i++) {
  Object item = queue.remove();
  if (item instanceof LocalDate) {
    var localDate = (LocalDate) item;
    ...
  } else if (item instanceof String) {
    var string = (String) item;
    ...
  } else if (item instanceof Intenger) {
    var integer = (Integer) item;
    ...
  } else {
    ...
  }
}
``` 

Yuk! By making our Queue class generic the user can specify the type of thing each Queue object will store when it is created, and the compiler will complain if the user tries to add something that is not of the specified type. But, you might say, that means each Queue object can only store one type of thing! Yes, but that's what you ought to be doing anyway. If you need a data structure that can store a variety of things (in terms of type), then you should consider creating a class to encapsulate them. Let's make our Queue class generic.

### Part 3

1. Add a type parameter (`<T>`) to the Queue class immediately following the class name. The type T will be determined when the class is instantiated so that if a Queue of LocalDates is created, then T will become LocalDate.<details>
    <summary>Show me</summary>

    ```java
    public class Queue<T> {
      ...
    }
    ```
</details>

2. In the Queue class have the add method accept an argument of type T instead of Object.<details>
    <summary>Show me</summary>

    ```java
    public void add(T item) throws Exception {
      if (numItems < capacity) {
        items[numItems++] = item;
      } else {
        throw new Exception("The queue is full");
      }
    }
    ```
</details>

3. In the Queue class have the remove method return an argument of type T instead of Object. Note that this will involve casting the first item in the array as a T.<details>
    <summary>Show me</summary>

    ```java
    public T remove() throws Exception {
      if (numItems > 0) {
        var firstItem = (T) items[0]; // <-- cast the item as a T
        for (var i = 1; i < numItems; i++) {
          items[i - 1] = items[i];
        }
        numItems--;
        return firstItem;
      } else {
        throw new Exception("There are no items in the queue");
      }
    }
    ```
</details>

4. Note that the compiler is now warning you about using a raw type in your QueueTest class. That's because Queue is now generic and so when a Queue object is created, it should specify what type of things it will store. Make the necessary changes to your QueueTest class so as to remove the compiler warnings/errors.<details>
    <summary>Show me</summary>

    ```java
    private Queue<LocalDate> queue;

    @BeforeEach
    public void setUp() {
      queue = new Queue<>(3);
    }

    @Test
    public void testAdd() throws Exception {
      queue.add(LocalDate.now());
      // queue.add("Hello");
      // queue.add(42);
    }
    ```
</details>

### Optional Extra

Flesh out the QueueTest class to ensure the Queue class works as expected and is bug-free. Then use Maven to install your app into the local Maven repo for use by other projects.
 
[<< back](../../../../README.md#generics)
