package org.example.movierater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieStoreIntegrationTest {

    private MovieStore store;

    @BeforeEach
    public void beforeEach() throws PersistenceException {
        var indices = new MovieFieldIndices.Builder()
                .setTitleIndex(1)
                .setGenreIndex(5)
                .setReleaseYearIndex(2)
                .build();
        store = new MovieStore(new CsvFileMovieRepo("./imdb_top_1000.csv", indices));
    }

    @Disabled
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
        assertEquals(250, movies.size());
        movies.stream().map(Movie::getTitle).forEach(System.out::println);
    }
}
