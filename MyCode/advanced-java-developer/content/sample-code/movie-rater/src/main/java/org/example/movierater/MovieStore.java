package org.example.movierater;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public List<Movie> findMoviesByPartialTitle(String partialTitle) {
        var matchingMovies = new LinkedList<Movie>();
        for (var movie : movies) {
            if (movie.getTitle().toLowerCase().contains(partialTitle.toLowerCase())) {
                matchingMovies.add(movie);
            }
        }
        return matchingMovies;
    }
}
