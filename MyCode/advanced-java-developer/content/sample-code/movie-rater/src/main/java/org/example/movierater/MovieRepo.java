package org.example.movierater;

import java.util.Set;

public interface MovieRepo {

    Set<Movie> getMovies() throws PersistenceException;
}
