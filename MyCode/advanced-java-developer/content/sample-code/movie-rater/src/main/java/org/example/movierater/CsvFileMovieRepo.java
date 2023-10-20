package org.example.movierater;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class CsvFileMovieRepo implements MovieRepo {

    private String filename;

    /*
     * TODO we have hard-coded column indicies which are fragile and non future-proof
     * TODO we need to handle or remove Movie IDs
     */

    public CsvFileMovieRepo(String filename) {
        this.filename = filename;
    }

    // TODO think about moving this into Movie as a static factory
    private Movie csvLineToMovie(String csvLine) {
        var parts = csvLine.split(",");
        // var id = Long.parseLong(parts[0]);
        var title = parts[1];
        var genre = parts[5];
        var releaseYear = Integer.parseInt(parts[2]);
        var movie = new Movie(title, genre, releaseYear);
        // movie.setId(id);
        return movie;
    }

    private Set<Movie> getMoviesBy(Predicate<String> predicate) throws PersistenceException {
        var movies = new HashSet<Movie>();
        try (var reader = Files.newBufferedReader(Path.of(filename))) {
            var line = reader.readLine();
            while (line != null) {
                if (predicate.test(line)) {
                    movies.add(csvLineToMovie(line));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new PersistenceException();
        }
        return movies;
    }

    @Override
    public Set<Movie> getMoviesByPartialTitle(String partialTitle) throws PersistenceException {
        return getMoviesBy(line -> {
             var parts = line.split(",");
             var title = parts[1];
             return title.toLowerCase().contains(partialTitle.toLowerCase());
        });
    }

    @Override
    public Set<Movie> getMoviesByGenre(String targetGenre) throws PersistenceException {
        return getMoviesBy(line -> {
            var parts = line.split(",");
            var genre = parts[5];
            return genre.equalsIgnoreCase(targetGenre);
        });
    }
}
