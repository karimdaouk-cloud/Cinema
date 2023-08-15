import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CinemaBookingApp extends JFrame {
    // Instance variables to store cinema, movie, and ticket information
    private Cinema cinema;
    private Movie[] movies;
    private int movieCount;
    private Ticket[] tickets;
    private int ticketCount;
    
// Class Constructor
    public CinemaBookingApp(Cinema cinema) {
        this.cinema = cinema;
        this.movies = new Movie[10]; //I assumed a maximum of 10 movies for simplicity
        this.movieCount = 0;
        this.tickets = new Ticket[50]; //I assumed a maximum of 50 tickets for simplicity
        this.ticketCount = 0;
        // Creating instances of Movie and adding them to the movies array
        // (i choosed 10 predefined movies for demonstration purposes)
        Movie starWars = new Movie("Star Wars", 150); //I assumed duration in minutes
        Movie barbie = new Movie("Barbie", 90);
        Movie theSimpsons = new Movie("The Simpsons", 120);
        Movie zombieLand = new Movie("Zombie Land", 105);
        Movie theWords = new Movie("The Words (2012)", 135);
        Movie catchMeIfYouCan = new Movie("Catch Me If You Can", 135);
        Movie wolfOfWallStreet = new Movie("Wolf of Wall Street", 180);
        Movie oppenheimer = new Movie("Oppenheimer", 160);
        Movie fastAndFurious8 = new Movie("Fast and Furious 8", 140);
        Movie kingKong = new Movie("King Kong", 165);
        movies[0] = starWars;
        movies[1] = barbie;
        movies[2] = theSimpsons;
        movies[3] = zombieLand;
        movies[4] = theWords;
        movies[5] = catchMeIfYouCan;
        movies[6] = wolfOfWallStreet;
        movies[7] = oppenheimer;
        movies[8] = fastAndFurious8;
        movies[9] = kingKong;
        
         // Setting up the GUI for the Cinema Booking App

        setTitle("Cinema Booking");//to set title for JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//to exit when the user close the JFtrame
        setLayout(new BorderLayout());
        // Label to display the cinema name
        JLabel nameLabel = new JLabel("Cinema: " + cinema.getName());
        add(nameLabel, BorderLayout.NORTH);
        // Center panel to display movie and ticket information
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        add(centerPanel, BorderLayout.CENTER);
        // Text area to display movie information
        JTextArea movieTextArea = new JTextArea(20, 30);
        movieTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(movieTextArea));
        // Text area to display ticket information
        JTextArea ticketTextArea = new JTextArea(20, 30);
        ticketTextArea.setEditable(false);
        centerPanel.add(new JScrollPane(ticketTextArea));
         // Button to book a ticket
        JButton bookButton = new JButton("Book Ticket");
        add(bookButton, BorderLayout.SOUTH);
          // ActionListener for the bookButton
        bookButton.addActionListener(new ActionListener() {
            // Show a dialog to select a movie from the available options
           
            public void actionPerformed(ActionEvent e) {
                Movie selectedMovie = (Movie) JOptionPane.showInputDialog(CinemaBookingApp.this,
                        "Select a movie:", "Movie Selection", JOptionPane.PLAIN_MESSAGE, null, movies, null);

                if (selectedMovie != null) {
                    // If a movie is selected, prompt for the number of seats to book
                    String seatNumberStr = JOptionPane.showInputDialog(CinemaBookingApp.this, "Enter the number of seats:");
                    try {
                        int seatNumber = Integer.parseInt(seatNumberStr);
                        double price = 10.0*seatNumber; // we assume a constant price for all tickets
                         // Create a new Ticket object and add it to the tickets array
                        Ticket ticket = new Ticket(selectedMovie, seatNumber, price);
                        tickets[ticketCount++] = ticket;
                        // Add the movie to the cinema schedule
                        cinema.addMovie(selectedMovie); // Add the movie to the cinema schedule

                        // Update the movie and ticket display
                        updateMovieTextArea(movieTextArea);
                        updateTicketTextArea(ticketTextArea);
                    } catch (NumberFormatException ex) {
                        //Display the GUI
                        JOptionPane.showMessageDialog(CinemaBookingApp.this, "Invalid seat number!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                     // Display an error message if no movie is selected
                    JOptionPane.showMessageDialog(CinemaBookingApp.this, "Please select a movie!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pack();//we used it for window to be resized to its preferred size
        setLocationRelativeTo(null);// sets the location of the window relative to the specified component 
    }
    
 // Method to update the ticket information in the GUI 
    private void updateMovieTextArea(JTextArea movieTextArea) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < movieCount; i++) {
            sb.append(movies[i]).append("\n");
        }
        movieTextArea.setText(sb.toString());
    }//refresh the movie list whenever a new movie is added to the cinema's schedule
 // Method to update the ticket information in the GUI
    private void updateTicketTextArea(JTextArea ticketTextArea) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ticketCount; i++) {
            sb.append(tickets[i]).append("\n");
        }
        ticketTextArea.setText(sb.toString());
    }
  // The main method to start the application
      public static void main(String[] args) {
        // Sample data for demonstration
        Cinema cinema = new Cinema("ABC Cinema");
 // Create some sample movies and add them to the cinema's schedule
        Movie movie1 = new Movie("movie 1", 120);
        Movie movie2 = new Movie("movie 2", 105);

        cinema.addMovie(movie1);
        cinema.addMovie(movie2);

        SwingUtilities.invokeLater(new Runnable() {
            // Create and show the CinemaBookingApp GUI
            public void run() {
                new CinemaBookingApp(cinema).setVisible(true);
            }
        });
    }
   
}




