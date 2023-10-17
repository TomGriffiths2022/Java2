<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Modular Applications Assignment

## Joke Service

[<< back](../../../../README.md#modular-applications)

You're going to take an existing non-modular joke service app (joker) and build for it a client. The joke service is written such that the user must first correctly answer a quiz question to obtain a token. The token may then be exchanged for a joke. You will also write a test to determine whether the joke service app is properly encapsulated. That is, can a token be generated and added to the token store without first answering a quiz question? You will then modularise the joke service app exposing only those classes needed by the users of the library. Download [this Maven project](joker.zip), unzip it, and open it in IntelliJ.

### Part 1

1. Use Maven to install the joker app into your local repository.<details>
    <summary>Show me</summary>

    ```
    $> # in the project's root directory
    $> mvn install
    ```
</details>

2. Create a new Maven project using the quickstart archetype named `joker-client`.

3. In the POM configure the maven-compiler-plugin for Java 17.<details>
    <summary>Show me</summary>

    ```xml
    <properties>
      <maven.compiler.source>17</maven.compiler.source>
      <maven.compiler.target>17</maven.compiler.target>
    </properties>
    ```
</details>

4. Add to the POM dependencies for joker (org.example:joker) and for junit (org.junit.jupiter:junit-jupiter).<details>
    <summary>Show me</summary>

    ```xml
    <dependencies>
      <dependency>
        <groupId>org.example</groupId>
        <artifactId>joker<artifactId>
        <version>1.0</version>
      </dependency>
      <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.8.2</version> <!-- or whatever is the latest version -->
        <scope>test</scope>
      </dependency>
    </dependencies>
    ```
</details>

5. Add a package to the src/main/java directory named `org.example.jokerclient`.

6. Add a class to the org.example.jokerclient package named `App` with a main method.

7. Inside the main method:
   - create a `QuestionAndAnswerService` instance (org.example.joker.questionsandanswers)
   - create a `JokeService` instance (org.example.joker.jokes)
   - create a `Scanner` instance to obtain input from stdin (java.util)
   - call the QuestionAndAnswerService instance's `getQuestion` method and assign the return value to a variable named `question`
   - prompt the user to answer the question and assign the return value to a variable named `answer`
   - call the QuestionAndAnswerService instance's `getToken` method passing in both the quesiton and answer, and assign the return value to a variable named token
   - call the JokeService instance's `getJoke` method passing in the token and write the return value to stdout
   - add exception handling as necessary and close the Scanner instance
    <details>
    <summary>Show me</summary>

    ```java
    var questionAndAnswerService = new QuestionAndAnswerService();
    var jokeService = new JokeService();
    var keyboard = new Scanner(System.in);
    var question = questionAndAnswerService.getQuestion();
    System.out.printf("%s ", question);
    var answer = keyboard.nextLine();
    try {
      var token = questionAndAnswerService.getToken(question, answer);
      System.out.println(jokeService.getJoke(token));
    } catch (BadQuestionAnswerException e) {
      System.out.println("That's the wrong answer!");
    }
    keyboard.close();
    ```
</details>

8. Run the App class and test that the joker library works as expected.

### Part 2

1. Add a package to the src/test/java directory named `org.example.jokerclient`.

2. Add a class to the org.example.jokerclient package in src/test/java named `LibraryEncapsulationTest`.

3. Add a method to the LibraryEncapsulationTest class named `testQandAServiceBypass` and annotate it with `@Test`. It should:
   - create a `TokenGenerator` instance (org.example.joker.tokens)
   - obtain a `TokenStore` instance (org.example.joker.tokens). Note that TokenStore is a singleton so you should call the static `getInstance` method.
   - create a `JokeService` instance (org.example.joker.jokes)
   - call the TokenGenerator instance's `generateToken` method and assign the return value to a variable named `token`
   - call the TokenStore instance's `addToken` method passing in the token
   - call the JokeService instance's `getJoke` method passing in the token
   - assert that calling JokeService::getJoke throws an `InvalidOrExpiredTokenException` given that the token used was obtained by bypassing the QuestionAndAnswerService
    <details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testQandAServiceBypass() {
      var tokenGenerator = new TokenGenerator();
      var tokenStore = TokenStore.getInstance();
      var jokeService = new JokeService();
      var token = tokenGenerator.generateToken();
      tokenStore.addToken(token);
      assertThrows(InvalidOrExpiredTokenException.class, () -> jokeService.getJoke(token));
    }
    ```
</details>

4. Run the test - it should fail. That is, you were able to obtain a joke without first answering a question obtained from the QuestionAndAnswerService. To put it another way, you were able to call on the TokenGenerator and TokenStore directly. This should not be possible. It enables users of the library to bypass its security mechanism. This problem might be solved by incorporating the token logic inside the other services but that's a poor option.

### Part 3

1. If not open already, open the joker app in IntelliJ.

2. Add to the src/main/java directory a file named `module-info.java`. Its contents should look like this:

    ```java
    module org.example.joker {

    }
    ```

3. Add lines to the module-info.java file such that only those classes in the exceptions, jokes, and questionsandanswers packages will be available to those apps using the library.<details>
    <summary>Show me</summary>

    ```java
    module org.example.joker {
      exports org.example.joker.exceptions;
      exports org.example.joker.jokes;
      exports org.example.joker.questionsandanswers;
    }
    ```
</details>

4. Update the version number in the POM from 1.0 to 2.0.<details>
    <summary>Show me</summary>

    ```xml
    <version>2.0</version>
    ```
</details>

5. Use Maven to install the new version of the joker app into your local repository.<details>
    <summary>Show me</summary>

    ```
    $> # in the project's root directory
    $> mvn clean install
    ```
</details>

6. If not open already, open the joker-client app in IntelliJ.

7. Update the POM so that the joker-client app is dependent on version 2.0 of the joker app.<details>
    <summary>Show me</summary>

    ```xml
    <dependency>
      <groupId>org.example</groupId>
      <artifactId>joker</artifactId>
      <version>2.0</version>
    </dependency>
    ```
</details>

8. Add to the src/main/java directory a file named `module-info.java`. Its contents should look like this:

    ```java
    module org.example.jokerclient {

    }
    ```

9. Add a line to the module-info.java file such that this module requires the joker module.<details>
    <summary>Show me</summary>

    ```java
    module org.example.jokerclient {
      requires org.example.joker;
    }
    ```
</details>

10. Run your LibraryEncapsulationTest once again. This time it will fail but with a different error - IllegalAccessError. The TokenGenerator and TokenStore classes are no longer accessible because the package, of which they are members, is not exported. It is no longer possible to bypass the QuestionAndAnswerService. 

[<< back](../../../../README.md#modular-applications)