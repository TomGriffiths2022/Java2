package org.example.mymovierater;

import java.util.Set;

public interface MovieRepo {

    Set<Movie>  getMovies() throws PersistenceException;
}
