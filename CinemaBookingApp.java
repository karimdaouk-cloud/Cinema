import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaBookingApp extends JFrame {
    private Cinema cinema;
    private Movie[] movies;
    private int movieCount;
    private Ticket[] tickets;
    private int ticketCount;
    

    public CinemaBookingApp(Cinema cinema) {
        this.cinema = cinema;
        this.movies = new Movie[10]; // Assumed a maximum of 10 movies for simplicity
        this.movieCount = 0;
        this.tickets = new Ticket[50]; // Assumed a maximum of 50 tickets for simplicity
        this.ticketCount = 0;
       /*Movies suggestions to add 
        moviename="Star wars";
        moviename="barbie";
        moviename="The simpsons";
        moviename="zombie land";
        moviename="the words(2012)";
        moviename="Catch me if you can";
        moviename="wolf of wallstreet";
        moviename="oppenheimer";
        moviename="fast and furious 8";
        moviename="king kong";
        */
        

        setTitle("Cinema Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Cinema: " + cinema.getName());
        add(nameLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        add(centerPanel, BorderLayout.CENTER);

        JTextArea movieTextArea = new JTextArea(20, 30);
        movieTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(movieTextArea));

        JTextArea ticketTextArea = new JTextArea(20, 30);
        ticketTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(ticketTextArea));

        JButton bookButton = new JButton("Book Ticket");
        add(bookButton, BorderLayout.SOUTH);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movie selectedMovie = (Movie) JOptionPane.showInputDialog(CinemaBookingApp.this,
                        "Select a movie:", "Movie Selection", JOptionPane.PLAIN_MESSAGE, null, movies, null);

                if (selectedMovie != null) {
                    String seatNumberStr = JOptionPane.showInputDialog(CinemaBookingApp.this, "Enter seat number:");
                    try {
                        int seatNumber = Integer.parseInt(seatNumberStr);
                        double price = 10.0; // Assuming a constant price for all tickets
                        Ticket ticket = new Ticket(selectedMovie, seatNumber, price);
                        tickets[ticketCount++] = ticket;
                        cinema.addMovie(selectedMovie); // Add the movie to the cinema schedule

                        // Update the movie and ticket display
                        updateMovieTextArea(movieTextArea);
                        updateTicketTextArea(ticketTextArea);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(CinemaBookingApp.this, "Invalid seat number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(CinemaBookingApp.this, "Please select a movie!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void updateMovieTextArea(JTextArea movieTextArea) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movieCount; i++) {
            sb.append(movies[i]).append("\n");
        }
        movieTextArea.setText(sb.toString());
    }

    private void updateTicketTextArea(JTextArea ticketTextArea) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ticketCount; i++) {
            sb.append(tickets[i]).append("\n");
        }
        ticketTextArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        // Sample data for demonstration
        Cinema cinema = new Cinema("ABC Cinema");

        Movie movie1 = new Movie("movie 1", 120);
        Movie movie2 = new Movie("movie 2", 105);

        cinema.addMovie(movie1);
        cinema.addMovie(movie2);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CinemaBookingApp(cinema).setVisible(true);
            }
        });
    }
}




