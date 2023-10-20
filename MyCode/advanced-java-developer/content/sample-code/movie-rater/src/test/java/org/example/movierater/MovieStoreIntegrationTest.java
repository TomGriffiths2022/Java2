package org.example.movierater;

import org.junit.jupiter.api.BeforeEach;

public class MovieStoreIntegrationTest {

    private MovieStore store;

    @BeforeEach
    public void beforeEach() throws PersistenceException {
        store = MovieStore.getInstance(new CsvFileMovieRepo("./movies.csv"));
    }

    // TODO code tests for each MovieStore method
}
