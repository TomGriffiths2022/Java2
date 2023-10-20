package org.example.movierater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieStoreIntegrationTest {

    private MovieStore store;

    @BeforeEach
    public void beforeEach() throws PersistenceException {
        store = new MovieStore(new CsvFileMovieRepo("./movies.csv"));
    }

    @Test
    public void testGetMovieById() throws NoSuchMovieException {
        var movie = store.getMovieById(1);
        assertAll(
                () -> assertEquals("The Blues Brothers", movie.getTitle()),
                () -> assertEquals("Comedy", movie.getGenre()),
                () -> assertEquals(1980, movie.getReleaseYear())
        );
    }

    @Test
    public void testFindMoviesByPartialTitle() throws PersistenceException {
        var movies = store.findMoviesByPartialTitle("The");
        assertAll(
                () -> assertEquals(2, movies.size()),
                () -> assertIterableEquals(
                        List.of("The Blues Brothers", "The Shawshank Redemption"),
                        movies.stream().map(Movie::getTitle).sorted().toList()
                )
        );
    }
}
