package org.example.movierater;

import java.util.Set;

public interface MovieRepo {

    Set<Movie> getMoviesByPartialTitle(String partialTitle) throws PersistenceException;

    Set<Movie> getMoviesByGenre(String genre) throws PersistenceException;
}
