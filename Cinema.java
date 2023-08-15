class Cinema {
    private String name;
    private Movie[] movies;
    private int movieCount;

    public Cinema(String name) {
        this.name = name;
        this.movies = new Movie[10];
        this.movieCount = 0;
    }

    public String getName() {
        return name;
    }

    public void addMovie(Movie movie) {
        if (movieCount < movies.length) {
            movies[movieCount++] = movie;
        } else {
            
        }
    }
}
