<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Advanced Java Developer

> [Generics](#generics)<br />
  [Collections](#collections)<br />
  [hashCode & equals and comapreTo/compare](#hashcode--equals-and-comparetocompare)<br />
  [Nested Classes](#nested-classes)<br />
  [Lambda Expressions and Method References](#lambda-expressions-and-method-references)<br />
  [Functional Programming](#functional-programming)<br />
  [Streams](#streams)<br />
  [Exceptions, Assertions, and Localisation](#exceptions-assertions-and-localisation)<br />
  [Modular Applications](#modular-applications)<br />
  [Concurrency](#concurrency)<br />
  [I/O](#io)<br />
  [NIO.2](nio.2)<br />
  [Annotations](#annotations)<br />
  [Reflection](#reflection)<br />
  [Security](#security)

**[Preparatory Exam - movie rater](content/exams/preparatory/movie-rater)**

## Generics

[Recommended reading](content/recommended-reading.md#generics)

- Generic types
- Generic methods
- Bounded type parameters
- Generics and inheritance
- Type inference
- Wildcards
- Type erasure
- Restrictions
- **[Assignment - custom queue](content/assignments/generics/custom-queue)**
- **[Assignment - array to list](content/assignments/generics/array-to-list)**

## Collections

[Recommended reading](content/recommended-reading.md#collections)

- The Collection interface hierarchy
- Iterable and Iterator
- Collection operations and traversal
- The Collections class
- Collection types (characteristics and implementations) incl. List, Set and Queue
- The Map interface hierarchy
- Map operations and traversal
- Map types (characteristics and implementations)
- **[Assignment - shopping cart](content/assignments/collections/shopping-cart)**

## hashCode & equals and compareTo/compare

[Recommended reading](content/recommended-reading.md#hashcode--equals-and-comparetocompare)

- The Object class
- Overriding toString
- Equality and membership testing
- Hashing collections
- Overriding hashCode and equals
- The Comparable and Comparator interfaces
- Searching and sorting
- Overriding compareTo and compare
- **[Assignment - flights](content/assignments/hashcode--equals-and-comparetocompare/flights)**

## Nested Classes

[Recommended reading](content/recommended-reading.md#nested-classes)

- Static nested classes
- Inner classes
- Shadowing
- Local classes
- Anonymous classes
- Variable capture and effectively final

## Lambda Expressions and Method References

[Recommended reading](content/recommended-reading.md#lambda-expressions-and-method-references)

- What is a lambda expression?
- Lambda use case: collection filter
- Lambda syntax
- What is a method reference?
- Method reference use case: collection sort
- Method reference types x 4
- **[Assignment - lambda expressions](content/assignments/lambda-expressions-and-method-references/lambda-expressions)**

## Functional Programming

[Recommended reading](content/recommended-reading.md#functional-programming)

- What is functional programming?
- Functions as first class objects
- Pure functions
- Higher order functions
- Immutable objects
- Java's functional interfaces
- Functional composition
- **[Assignment - filter method](content/assignments/functional-programming/filter-method)**

## Streams

[Recommended reading](content/recommended-reading.md#streams)

- What is a stream?
- Obtaining/creating a stream
- Intermediate (non-terminal) operations
- Terminal operations
- The Optional class
- The Collectors class
- Stream concatenation
- Primitive streams
- **[Assignment - course processor](content/assignments/streams/course-processor)**

## Exceptions, Assertions, and Localisation

[Recommended reading](content/recommended-reading.md#exceptions-assertions-and-localisation)

- Exceptions (a review)
- Custom exceptions
- Try with resources
- Assertions
- Dates and times
- Internationalisation and localisation
- Resource bundles

## Modular Applications

[Recommended reading](content/recommended-reading.md#modular-applications)

- Modules (a review)
- Module types
- Analysing dependencies
- Migrating an existing application
- Creating a service
- **[Assignment - joke service](content/assignments/modular-applications/joke-service)**

## Concurrency

[Recommended reading](content/recommended-reading.md#concurrency)

- Low-level threading incl. Runnable, sleep, join
- The Java memory model
- Threading problems incl. race conditions, deadlock, and starvation
- Immutable objects and ThreadLocal
- Thread synchronization incl. volatile, synchronized, atomic data types, and locks
- ExecutorService, thread pools, Callable, and Future
- Concurrent and immutable collections
- Parallel streams
- **[Assignment - movie fetcher](content/assignments/concurrency/movie-fetcher)**

## I/O 

[Recommended reading](content/recommended-reading.md#io)

- Files and directories
- I/O streams
- Common operations
- Binary data, character data, and serialisation
- Console I/O

## NIO.2

[Recommended reading](content/recommended-reading.md#nio.2)

- What is NIO?
- Paths
- Manipulating the filesystem
- File attributes
- Listing, traversing, and searching a directory

## Annotations

[Recommended reading](content/recommended-reading.md#annotations)

- What is an annotation?
- Form and application
- Standard annotations
- Declaring an annotation

## Reflection

[Recommended reading](content/recommended-reading.md#reflection)

- What is reflection?
- Modules, classes, constructors, fields, and methods
- Private members
- Annotations
- Generics and arrays
- Dynamic proxies
- Dynamic class loading
- **[Assignment - validator](content/assignments/reflection/validator)**

## Security

[Recommended reading](content/recommended-reading.md#security)

- Securing objects
- Injection and input validation
- Handling confidential information
- Serialising and deserialising objects
- Sensitive objects
- Preventing denial of service attacks

## Optional Modules

### Build Tools

[Recommended reading](content/recommended-reading.md#build-tools)

- What is a build tool?
- Maven
  - Installation
  - The POM
  - Directory structure
  - Dependencies
  - Repositories
  - Lifecycles, phases, and goals
  - Profiles
  - Plugins
- **[Assignment - lombok pojo](content/assignments/build-tools/lombok-pojo)**

### Logging

[Recommended reading](content/recommended-reading.md#logging)

- The Logger
- Logger hierarchy
- Log levels
- Formatters
- Filters
- Appenders
- Log4j2
- Logback
- SLF4J
- **[Assignment - book store](content/assignments/logging/book-store)**