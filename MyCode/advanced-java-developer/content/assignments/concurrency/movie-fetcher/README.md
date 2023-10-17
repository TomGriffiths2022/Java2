<img src="https://github.com/stayahead-training/shared/blob/master/stayahead.png" />

# Concurrency Assignment

## MovieFetcher

[<< back](../../../../README.md#concurrency)

You're going to take an existing app that includes a method for fetching movie data from omdbapi.com and test the fetching of n movies with one thread and then again with many threads. Download [this Maven project](movie-fetcher.zip), unzip it, and open it in IntelliJ. The app comprises a MovieFetcher class with static fetchMovie method and an associated unit test.

**Note that you will first have to register for a free API key at [omdbapi.com](https://www.omdbapi.com/apikey.aspx).**

1. Add to the MovieFetcherTest class a method named `testFetchMovieOneThread` and annotate it with `@Test`. In it you should use the `getRandomImdbIDs` method to obtain a List of 10 IMDB IDs. For each ID you should call MovieFetcher::fetchMovie passing in your OMDB API key and the IMDB ID, and then write each movie to the console. When you run the test make a note of how long it takes to fetch the movies.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFetchMovieOneThread() throws IOException {
        getRandomImdbIDs(10).forEach(imdbID -> {
            try {
                var movie = fetchMovie(omdbApiKey, imdbID);
                System.out.println(movie);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
    ```
</details>

2. Add to the MovieFetcherTest class a method named `testFetchMovieManyThreads` and annotate it with `@Test`. In it you should use the `getRandomImdbIDs` method to obtain a List of 10 IMDB IDs. For each ID you should have a thread call MovieFetcher::fetchMovie passing in your OMDB API key and IMDB ID. You might choose to create low-level Threads or exploit ExecutorService. Either way you must force the main thread to wait for all other threads to complete their work. As you did previously, you should write each movie to the console. When you run the test make a note of how long it takes to fetch the movies.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFetchMovieManyThreads() throws IOException {
        var service = Executors.newFixedThreadPool(10);
        var futures = new LinkedList<Future<String>>();
        getRandomImdbIDs(numIDs).forEach(imdbID -> {
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
    ```
</details>

3. What effect does scale have on the performance difference between the one thread and many thread versions? Modify your code so as to fetch 50 movies and make a note of the time difference.

4. If you used an ExecutorService in your solution to step 2 above then code another test that uses low-level Threads and compare the relative performances. Likwise if you previously used low-level Threads then code another test that uses an ExecutorService and compare the pair.

5. Code another test that fetches the movies using a parallel stream.<details>
    <summary>Show me</summary>

    ```java
    @Test
    public void testFetchMovieManyTheadsWithParallelStream() throws IOException {
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
    ```
</details>

6. Is it possible to benefit from having more threads than your CPU has cores? Why/why not? 

[<< back](../../../../README.md#concurrency)