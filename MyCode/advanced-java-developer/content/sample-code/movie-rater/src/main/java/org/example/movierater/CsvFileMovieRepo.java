package org.example.movierater;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class CsvFileMovieRepo implements MovieRepo {

    // TODO we need to handle or remove Movie IDs

    private String filename;
    private MovieFieldIndices indices;

    private static final String CSV_DELIMITER = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";

    public CsvFileMovieRepo(String filename, MovieFieldIndices indices) {
        this.filename = filename;
        this.indices = indices;
    }

    // TODO think about moving this into Movie as a static factory
    private Movie csvLineToMovie(String csvLine) {
        var parts = csvLine.split(CSV_DELIMITER, -1);
        // var id = Long.parseLong(parts[mapper.getIdFieldIndex()]);
        var title = parts[indices.getTitleIndex()];
        var genre = parts[indices.getGenreIndex()];
        var releaseYear = Integer.parseInt(parts[indices.getReleaseYearIndex()]);
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
             var parts = line.split(CSV_DELIMITER, -1);
             var title = parts[indices.getTitleIndex()];
             return title.toLowerCase().contains(partialTitle.toLowerCase());
        });
    }

    @Override
    public Set<Movie> getMoviesByGenre(String targetGenre) throws PersistenceException {
        return getMoviesBy(line -> {
            var parts = line.split(CSV_DELIMITER, -1);
            var genre = parts[indices.getGenreIndex()];
            return genre.equalsIgnoreCase(targetGenre);
        });
    }
}
