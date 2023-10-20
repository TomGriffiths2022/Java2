package org.example.movierater;

public class MovieFieldIndices {

    private int idIndex;
    private int titleIndex;
    private int genreIndex;
    private int releaseYearIndex;

    private MovieFieldIndices(int idIndex, int titleIndex, int genreIndex, int releaseYearIndex) {
        this.idIndex = idIndex;
        this.titleIndex = titleIndex;
        this.genreIndex = genreIndex;
        this.releaseYearIndex = releaseYearIndex;
    }

    public static class Builder {

        private int idIndex;
        private int titleIndex;
        private int genreIndex;
        private int releaseYearIndex;

        public Builder setIdIndex(int idIndex) {
            this.idIndex = idIndex;
            return this;
        }

        public Builder setTitleIndex(int titleIndex) {
            this.titleIndex = titleIndex;
            return this;
        }

        public Builder setGenreIndex(int genreIndex) {
            this.genreIndex = genreIndex;
            return this;
        }

        public Builder setReleaseYearIndex(int releaseYearIndex) {
            this.releaseYearIndex = releaseYearIndex;
            return this;
        }

        public MovieFieldIndices build() {
            return new MovieFieldIndices(idIndex, titleIndex, genreIndex, releaseYearIndex);
        }
    }

    public int getIdIndex() {
        return idIndex;
    }

    public int getTitleIndex() {
        return titleIndex;
    }

    public int getGenreIndex() {
        return genreIndex;
    }

    public int getReleaseYearIndex() {
        return releaseYearIndex;
    }
}
