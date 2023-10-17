package org.example;

import java.util.Objects;

public class Movie {

    private long id;
    private String title;
    private String genre;
    private int releaseYear;
    private int ratings;

    private static long nextId = 1;
    public Movie(String title, String genre, int releaseYear, int ratings) {
        this.id = nextId++;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.ratings = ratings;
    }

    // Do this by right-clicking and select "Generate" -> equals() and hashCode()
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Movie movie = (Movie) object;
        return getReleaseYear() == movie.getReleaseYear() && java.util.Objects.equals(getTitle(), movie.getTitle()) && java.util.Objects.equals(getGenre(), movie.getGenre());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getGenre(), getReleaseYear());
    }

    public long getId() { return id; }

    public void setId(int id) { this.id = id;}

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public int getReleaseYear() { return releaseYear; }

    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
}
