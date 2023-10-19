package org.example.movierater;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MovieStore {

    private Set<Movie> movies;

    public MovieStore() {
        this.movies = new HashSet<>();
    }

    private long getNextId() {
        var nextId = 0L;
        for (var movie : movies) {
            if (movie.getId() > nextId) {
                nextId = movie.getId();
            }
        }
        return nextId + 1;
    }

    public Movie addMovie(Movie movie) throws DuplicateMovieException {
        if (movies.contains(movie)) {
            throw new DuplicateMovieException();
        }
        movie.setId(getNextId());
        movies.add(movie);
        return movie;
    }

    public Movie getMovieById(long id) throws NoSuchMovieException {
        for (var movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        throw new NoSuchMovieException();
    }

    private List<Movie> findMoviesBy(Predicate<Movie> predicate) {
        return movies.stream().filter(predicate).toList();
    }

    public List<Movie> findMoviesByPartialTitle(String partialTitle) {
        return findMoviesBy(movie -> movie.getTitle().toLowerCase().contains(partialTitle.toLowerCase()));
    }

    public List<Movie> findMoviesByGenre(String genre) {
        return findMoviesBy(movie -> movie.getGenre().equalsIgnoreCase(genre));
    }

    public List<Movie> findMoviesByReleaseYearInRange(int startYearInclusive, int endYearExclusive) {
        return findMoviesBy(movie -> movie.getReleaseYear() >= startYearInclusive &&
                movie.getReleaseYear() < endYearExclusive);
    }

    public List<Movie> findMoviesByGenreAndReleaseYearInRange(String genre, int startYearInclusive, int endYearExclusive) {
        return findMoviesBy(movie -> {
           return movie.getGenre().equalsIgnoreCase(genre) &&
                   movie.getReleaseYear() >= startYearInclusive &&
                   movie.getReleaseYear() < endYearExclusive;
        });
    }
}
