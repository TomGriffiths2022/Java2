package org.example.movierater;

import org.junit.jupiter.api.BeforeEach;

public class MovieStoreIntegrationTest {

    private MovieStore store;

    @BeforeEach
    public void beforeEach() throws PersistenceException {
        store = MovieStore.getInstance(new CsvFileMovieRepo("./movies.csv"));
    }

    @ParameterizedTest(name = "Threads = {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testFetchMovieManyThreads(int threadCount) throws IOException {
        var service = Executors.newFixedThreadPool(threadCount);
        var futures = new LinkedList<Future<String>>();
        getRandomImdbIDs(5).forEach(imdbID -> {
            futures.add(service.submit(() -> {
                try {
                    return fetchMovie(omdbApiKey, imdbID);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }));
        });
        futures.forEach(future -> {
            try {
                var movie = future.get();
                System.out.println(movie);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @ParameterizedTest(name = "Threads = {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testFetchMovieManyThreadsFiftyMovies(int threadCount) throws IOException {
        var service = Executors.newFixedThreadPool(threadCount);
        var futures = new LinkedList<Future<String>>();
        getRandomImdbIDs(50).forEach(imdbID -> {
            futures.add(service.submit(() -> {
                try {
                    return fetchMovie(omdbApiKey, imdbID);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }));
        });
        futures.forEach(future -> {
            try {
                var movie = future.get();
                System.out.println(movie);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }


    @Test
    void testFetchMovieManyTheadsWithParallelStream() throws IOException {
        getRandomImdbIDs(50).parallelStream()
                .map(imdbID -> {
                    try {
                        return fetchMovie(omdbApiKey, imdbID);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .forEach(System.out::println);
    }
}
