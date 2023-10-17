<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Preparatory Exam

## Movie Rater

[<< back](../../../../README.md)

Your task is to build an object-oriented app to store, search, and rate movies. You needn't write the movies to persistent storage, nor should you bother with a UI (for now).

Each movie should have an ID, a title, a genre, a release year, and ratings (no stars, one star, two stars, etc). The movie store should provide for adding new movies but should not permit duplicates. The movie store should further provide for finding movies by ID, partial title, exact title, genre, release year (between two given years), and average rating.

Try to avoid writing methods that (may) return null. If an attempt is made to add a duplicate movie or to find a movie with a non-existent ID then your app should throw an exception.

The app should comprise at least two classes - Movie and MovieStore.

Time permitting you should test the movie store by coding and executing a JUnit class.

[<< back](../../../../README.md)