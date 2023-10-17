<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Logging Assignment

## Book Store

[<< back](../../../../README.md#logging)

You're going to take an existing book store app and add logging to it. Download [this Maven project](book-store.zip), unzip it, and open it in IntelliJ. The app comprises two classes - Book and BookStore. It also has a test class for BookStore. Book is a simple POJO class. BookStore has a list of books and methods for adding and finding books. BookStoreTest tests the adding of a new book, the adding of a duplicate book, and the finding of books matching a given partial title.

### Part 1

1. Have the editor add a toString method to the Book class that includes all the Book's fields. toString is useful in logging as it enables you to easily include relevant data to the log message.<details>
    <summary>Show me</summary>

    ```java
    @Override
    public String toString() {
      return "Book{" +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        '}';
    }
    ```
</details>

2. Add to the Book class a static final Logger field named `logger` with a conventional name (that is, the fully qualified name of the class, e.g. org.example.bookstore.models.Book).<details>
    <summary>Show me</summary>

    ```java
    private static final Logger logger = Logger.getLogger(Book.class.getName());
    ```
</details>

3.  When a Book object is constructed log an INFO message that includes the constructor arguments.<details>
    <summary>Show me</summary>

    ```java
    public Book(String title, String author) {
      logger.info(String.format("Constructing Book with title=%s and author=%s\n", title, author)); // <--
      this.title = title;
      this.author = author;
    }
    ```
</details>

4. Add to the BookStore class a static final Logger field named `logger` with a conventional name.<details>
    <summary>Show me</summary>

    ```java
    private static final Logger logger = Logger.getLogger(BookStore.class.getName());
    ```
</details>

5. When a Book is added to the store log an INFO message that includes the Book in question.<details>
    <summary>Show me</summary>

    ```java
    public addBook(Book book) {
      logger.info(String.format("Adding Book: %s\n", book)); // <--
      if (!bookIsAlreadyInTheStore(book.getTitle())) {
        books.add(book);
      }
    }
    ```
</details>

6. If the Book passed to the addBook method is a duplicate then log a WARNING message that includes the Book in question.<details>
    <summary>Show me</summary>

    ```java
    public addBook(Book book) {
      logger.info(String.format("Adding Book: %s\n", book));
      if (!bookIsAlreadyInTheStore(book.getTitle())) {
        books.add(book);
      } else {
          logger.warning(String.format("Tried to add a duplicate book: %s\n", book)); // <-- 
      }
    }
    ```
</details>

7. Execute all the tests in the BookStoreTest class. You should see that your log messages are written to the console. The loggers you created/obtained in the Book and BookStore classes each have a console handler attached by default. That is, the logger passes its log records to a console handler and it formats them and writes them to stdout. 

   The next step is to configure logging such that your loggers write when and where you want them to. We could do that by configuring each logger programmatically, but it's more common to specify the configuration in a properties file. 

### Part 2

1. Create a file in src/main/resources named `logging.properties`.

2. Let's assume we want each logger (Book and BookStore) to write all of its log records to file, and that only WARNING and SEVERE level messages are to be written to the console. Note that this is not necessarily conventional or recommended - each scenario is different. In logging.properties assign each logger (Book and BookStore) both file and console handlers.<details>
    <summary>Show me</summary>

    ```
    org.example.bookstore.models.Book.handlers=java.util.logging.FileHandler java.util.logging.ConsoleHandler
    org.example.bookstore.stores.BookStore.handlers=java.util.logging.FileHandler java.util.logging.ConsoleHandler
    ```
</details>

3. Each FileHandler must be assigned a pattern (effectively a file path). If a relative path is given then the file will be written to the directory specified by the `user.home` system property. In logging.properties assign a pattern to the FileHandler class named `bookstore.log`.<details>
    <summary>Show me</summary>

    ```
    java.util.logging.FileHandler.pattern=bookstore.log
    ```
</details>

4. Now to have the LogManager read the configuration specified in logging.properties. This can be done by setting an option on the VM but we're going to do it programmatically. In normal circumstances you'd probably do this in your main class but, as we don't have one, we're going to do it in BookStoreTest. Copy and paste the following code into BookStoreTest:

   ```java
   static {
     InputStream inputStream = BookStoreTest.class.getResourceAsStream("/logging.properties");
     try {
       LogManager.getLogManager().readConfiguration(inputStream);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
   ```

   This little snippet is a static initialiser. The code inside the static block is executed once only when the class is loaded into memory (note that a class is loaded only once, no matter how many times it is instantiated). Inside the block we obtain an InputStream for the logging.properties file, then we pass that to the LogManager's readConfiguration method. The nice thing about this is that we don't have to bother with absolute paths. Java will instead look for a file named logging.properties on the classpath.

5. Execute again all the tests in the BookStoreTest class. Look carefullty at the contents of the project root directory (you may have to click on it to have IntelliJ refresh it). There are two log files because we assigned a FileHandler to each logger - one file written by the Book logger's FileHandler, and another written by the BookStore logger's FileHandler. This is not what we wanted.

6. In logging.properties remove the FileHandler from each of your loggers, then specify an additional logger named `org.example.bookstore` and assign it a FileHandler.<details>
    <summary>Show me</summary>

    ```
    org.example.bookstore.handlers=java.util.logging.FileHandler
    org.example.bookstore.models.Book.handlers=java.util.logging.ConsoleHandler
    org.example.bookstore.stores.BookStore.handlers=java.util.logging.ConsoleHandler
    ```
</details>

7. Delete the existing log files and execute again all the tests in the BookStoreTest class. This time there is only one log file that contains all of the log records produced by both loggers. Why? The log records produced by both loggers are propogated up to the logger named org.example.bookstore and its FileHandler writes them to file.

8. By default each FileHandler formats its log records as XML. In logging.properties assign the SimpleFormatter to the FileHandler class.<details>
    <summary>Show me</summary>

    ```
    java.util.logging.FileHandler.formatter=java.util.logging.SimpleFormatter
    ```
</details>

9. Finally, at the outset we said that we wanted only WARNING and SEVERE messages to be written to the console but that is not currently the case. In logging.properties assign the WARNING level to the ConsoleHandler class.<details>
    <summary>Show me</summary>

    ```
    java.util.logging.ConsoleHandler.level=WARNING
    ```
</details>

10. Delete the existing log file and execute again all the tests in the BookStoreTest class. Make sure that the records in the log file are simply formatted, and that only WARNING + level messages are written to the console.

[<< back](../../../../README.md#logging)