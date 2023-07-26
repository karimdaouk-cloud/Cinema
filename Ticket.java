class Ticket {
    private Movie movie;
    private int seatNumber;
    private double price;

    public Ticket(Movie movie, int seatNumber, double price) {
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Ticket for " + movie.getTitle() + " - Seat: " + seatNumber + " - Price: $" + price;
    }
}