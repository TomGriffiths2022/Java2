# Case Study - Movie Rater

## Part 1

See preparatory exam on the GitHub repo.

## Part 2

Have your Movie class override hashCode and equals as you see fit. Might this help your MovieService to ensure duplicates are not added?

## Part 3

Your MovieStore/Service class is required to provide methods like findByTitle, findByGenre etc. These methods are likely to be very similar. To reduce duplication code one findBy method that accepts a strategy for filtering the collection of movies. The other methods should call this one. Make use of lambdas. 

Hint: the Java standard library includes a bunch of useful functional interfaces (see java.util.function). You might choose to use one of those.

## Part 4

Refactor your MovieStore/Service to make use of Streams. This may require the chaning of only one method.

## Part 5

Make some notes about:
- What is wrong with the code that has been added to read Movies from file
  - The Utils method is doing too much
  - We ought not to have a Utils class
  - MovieStore should not deal with IOException
  - The Utils method name gives away the tech.
  - We ought not to perform logic in a constructor
  - Low-level config data should not be hard-coded
- How the code might be refactored to improve it
  - Abstract the Utils method
  - Add a class for dealing with Movie persistence (including abstraction)
  - Code the MovieStore to accept an instance of a Movie persistence class
  - Catch IOException and re-throw as something else
  - Maybe use a static factory method to build the MovieStore
  - Externalise the filename

By all means write some code if you want to.

## Part 6

Make a change to your MovieRepo such that it has two methods:
- getMoviesByPartialTitle
- getMoviesByGenre


The implementation of these methods in your CsvFileMovieRepo should exploit BufferedReader to ensure the app can handle a very large file of movies. In each case a List/Set of Movies should be returned.

Make some changes to your MovieStore such that:
- the Set of Movies is empty initially and is only populated when the findByPartialTitle or findByGenre method is called.
- the other findBy methods should be renamed filterBy* and should operate on the data in memory.