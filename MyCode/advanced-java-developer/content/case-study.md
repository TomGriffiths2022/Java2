# Case Study - Movie Rater

## Part 1

See preparatory exam on the GitHub repo.

## Part 2

Have your Movie class override hashCode and equals as you see fit. Might this help your MovieService to ensure duplicates are not added?

## Part 3

Your MovieStore/Service class is required to provide methods like findByTitle, findByGenre etc. These methods are likely to be very similar. To reduce duplication code one findBy method that accepts a strategy for filtering the collection of movies. The other methods should call this one. Make use of lambdas. 

Hint: the Java standard library includes a bunch of useful functional interfaces (see java.util.function). You might choose to use one of those.