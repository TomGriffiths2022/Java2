package org.example.movierater;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class MovieStore {

    // TODO we need to handle or remove Movie IDs

    private Set<Movie> movies;
    private MovieRepo repo;

    public MovieStore(MovieRepo repo) {
        this.repo = repo;
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

    private List<Movie> filterMoviesBy(Predicate<Movie> predicate) {
        return movies.stream().filter(predicate).toList();
    }

    public List<Movie> findMoviesByPartialTitle(String partialTitle) throws PersistenceException {
        this.movies = repo.getMoviesByPartialTitle(partialTitle);
        return movies.stream().toList();
    }

    public List<Movie> findMoviesByGenre(String genre) throws PersistenceException {
        this.movies = repo.getMoviesByGenre(genre);
        return movies.stream().toList();
    }

    public List<Movie> filterMoviesByReleaseYearInRange(int startYearInclusive, int endYearExclusive) {
        return filterMoviesBy(movie -> movie.getReleaseYear() >= startYearInclusive &&
                movie.getReleaseYear() < endYearExclusive);
    }

    public List<Movie> filterMoviesByGenreAndReleaseYearInRange(String genre, int startYearInclusive, int endYearExclusive) {
        return filterMoviesBy(movie -> {
           return movie.getGenre().equalsIgnoreCase(genre) &&
                   movie.getReleaseYear() >= startYearInclusive &&
                   movie.getReleaseYear() < endYearExclusive;
        });
    }

    // TODO add more filter methods
}
