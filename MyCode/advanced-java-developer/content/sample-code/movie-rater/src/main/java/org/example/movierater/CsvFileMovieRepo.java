package org.example.movierater;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CsvFileMovieRepo implements MovieRepo {

    private String filename;

    public CsvFileMovieRepo(String filename) {
        this.filename = filename;
    }

    // TODO think about moving this into Movie as a static factory
    private Movie csvLineToMovie(String csvLine) {
        var parts = csvLine.split(",");
        var id = Long.parseLong(parts[0]);
        var title = parts[1];
        var genre = parts[2];
        var releaseYear = Integer.parseInt(parts[3]);
        var movie = new Movie(title, genre, releaseYear);
        movie.setId(id);
        return movie;
    }

    @Override
    public Set<Movie> getMovies() throws PersistenceException {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            // TODO modify PersistenceException to enable the wrapping of the original exception
            throw new PersistenceException();
        }
        return lines.stream().map(this::csvLineToMovie).collect(Collectors.toSet());
    }
}
