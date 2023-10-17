<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# hashCode & equals and compareTo/compare Assignment

## Flights

[<< back](../../../../README.md#hashcode--equals-and-comparetocompare)

You're going to code a class to represent flights and then test it for use in lists, sets, and maps. The purpose of this is to demonstrate the importance of overridding the hashCode and equals methods inherited from java.lang.Object.

### Part 1

1. Create a new Maven project using the quickstart archetype named `flights`.

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

4. Add a package to the src/main/java directory named `org.example.flights`.

5. Add a class to the org.example.flights package named `Flight`.

6. Each flight is comprised of an ID, a number (which may contain both alpha and numeric characters), an airline, a source, a destination, and a date and time. Add fields to the Flight class to represent these attributes.<details>
    <summary>Show me</summary>

    ```java
    private long id;
    private String number;
    private String airline;
    private String src;
    private String dest;
    private LocalDateTime dateAndTime;
    ```
</details>

7. Add a static field to the Flight class named `nextId` that will be used to generate a unique ID for each Flight object.<details>
    <summary>Show me</summary>

    ```java
    private static long nextId = 1L;
    ```
</details>

8. Add a constructor to the Flight class that accepts one argument for each field except the ID and that initialises all fields appropriately.<details>
    <summary>Show me</summary>

    ```java
    public Flight(String number, String airline, String src, String dest, LocalDateTime dateAndTime) {
      this.id = nextId++;
      this.number = number;
      this.airline = airline;
      this.src = src;
      this.dest = dest;
      this.dateAndTime = dateAndTime;
    }
    ```
</details>

9. Add to the Flight class getter and setter methods for all fields excluding only the setter method for ID.<details>
    <summary>Show me</summary>

    ```java
    public long getId() {
      return id;
    }

    public String getNumber() {
      return number;
    }

    public void setNumber(String number) {
      this.number = number;
    }

    public String getAirline() {
      return airline;
    }

    public void setAirline(String airline) {
      this.airline = airline;
    }

    public String getSrc() {
      return src;
    }

    public void setSrc(String src) {
      this.src = src;
    }

    public String getDest() {
      return dest;
    }

    public void setDest(String dest) {
      this.dest = dest;
    }

    public LocalDateTime getDateAndTime() {
      return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
      this.dateAndTime = dateAndTime;
    }
    ```
</details>

### Part 2

1. Add a package to the src/test/java directory named `org.example.flights`.

2. Add a class to the org.example.flights package named `FlightTest`.

3. Add two fields of type Flight to the FlightTest class named `f1` and `f2`.<details>
    <summary>Show me</summary>

    ```java
    private Flight f1, f2;
    ```
</details>

4. Add a method to the FlightTest class named setUp and annotate it with `@BeforeEach`. It should initialise each instance field with a new Flight object. Note that each Flight object should be identical.<details>
    <summary>Show me</summary>

    ```java
    @BeforeEach
    public void setUp() {
      var dateAndTime = LocalDateTime.of(2020, 7, 27, 16, 0, 0);
      f1 = new Flight("BA123", "British Airways", "LHR", "CDG", dateAndTime);
      f2 = new Flight("BA123", "British Airways", "LHR", "CDG", dateAndTime);
    }
    ```
</details>

5. Add a method to the FlightTest class named `testListContainsFlight` and annotate it with `@Test`. It should initialise a List of Flights comprising f1 only. It should then assert that the List has f2. This test *should* pass because the object referenced by f2 is identical to that referenced by f1.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testListContainsFlight() {
      var flights = List.of(f1); // of is a factory method that creates an unmodifiable List composed of the given arguments 
      assertTrue(flights.contains(f2));
    }
    ```
</details>

6. Add a method to the FlightTest class named `testDuplicateFlightsInSet` and annotate it with `@Test`. It should initialise a Set of Flights comprising both f1 and f2. It should then assert that the size of the Set is 1. This test *should* pass because the Set's add method will only add the given object if it is not already present in the Set. As f1 and f2 reference identical Flight objects, only the first one should be added.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testDuplicateFlightsInSet() {
      var uniqueFlights = new HashSet<Flight>();
      uniqueFlights.add(f1);
      uniqueFlights.add(f2);
      assertEquals(1, uniqueFlights.size());
    }
    ```
</details>

7. Add a method to the FlightTest class named `testDuplicateFlightsAsMapKeys` and annotate it with `@Test`. It should initialise a Map of Flights (keys) and Integers (values) comprising the following key value pairs: f1: 50 and f2: 75. Let's assume that the value represents the number of passengers booked on a given flight. The method should then assert that the value associated with the key f1 is 75. This test *should* pass because the second call to the Map's put method should overwrite the value associated with the given flight. Remember that Map keys must be unique and that f1 and f2 reference identical Flight objects.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testDuplicateFlightsAsMapKeys() {
        var flightMap = new HashMap<Flight, Integer>();
        flightMap.put(f1, 50);
        flightMap.put(f2, 75);
        assertThat(flightMap.get(f1), is(75));
    }
    ```
</details>

8. Execute Maven's test phase.

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

    All of your tests should fail! 
    
    The List test fails because membership testing uses the equals method to determine if the List contains the given object. But the equals method, as inherited from java.lang.Object, tests for equality using the == operator thereby testing for equality of reference, not for equality of content. The membership test effectively performs `f1.equals(f2)` which returns false because the variables reference distinct objects.

    The Set test fails for the same reason. When adding to a Set, the add method uses the object's equals method to determine if it is already present in the Set. As we know, given the default behaviour of the equals method, `f1.equals(f2)` returns false meaning that the Set adds both Flight objects despite their being identical.

    The Map test also fails but for a slightly different (and more complicated) reason. As the Flight objects referenced by f1 and f2 are identical they *should* generate identical hash codes. When putting a key value pair into a Map it uses both hashCode and equals methods to determine whether it should add a new key value pair or update an existing value. The hash code identifies the bucket into which the key is placed, and the equals method is used to determine if said key already exists in the bucket. But the hashCode method, as inherited from java.lang.Object, generates a code based on the object's memory address and, as f1 and f2 reference distinct objects, they generate distinct hash codes. In this case the second call to the Map's put method adds a new key value pair instead of updating the value associated with the given key.

### Part 3

1. Have the editor help you to override the hashCode and equals methods for the Flight class. The difficult part is in choosing which fields to include. The fields you choose should be those that determine uniqueness. That is, which fields make a Flight object unique? It's tempting to say ID but that would be wrong. Every Flight object will have a unique ID therefore every flight will be unique, i.e. it's not possible for the user to create duplicate flights - really?! Have a think about it and look at the solution if you're not sure. Note that, if you're using IntelliJ, we recommend applying the Java 7+ template.<details>
    <summary>Show me</summary>

    ```java
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Flight flight = (Flight) o;
      return Objects.equals(number, flight.number) &&
        Objects.equals(dateAndTime, flight.dateAndTime);
    }

    @Override
    public int hashCode() {
      return Objects.hash(number, dateAndTime);
    }
    ```
</details>

2. Execute again Maven's test phase.

    `$> mvn test`<br />

    All of your tests should now pass!

    The List and Set tests pass because `f1.equals(f2)` is now testing for equality of content. And the Map test passes because each Flight object generates the same hash code (the Map looks for the key in the right bucket) and, because the equals method tests for equality of content, the Map correctly determines that the key is already present and so updates its associated value.

### Optional Extra

Use Maven to install your app into the local Maven repo for use by other projects. 

[<< back](../../../../README.md#hashcode--equals-and-comparetocompare)