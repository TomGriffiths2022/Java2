package org.example;

import java.util.HashMap;
public interface MovieValidator {

    boolean validMovie(Movie movie, HashMap<Integer, Movie> movieList);
}
