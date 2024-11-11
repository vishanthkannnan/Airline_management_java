import java.awt.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AdminGUI extends JFrame {
    private final BookingManager bookingManager;
    private final FlightManager flightManager;
    private JTextArea flightDisplayArea;
    private JTextArea availableFlightsArea;

    public AdminGUI() {
        bookingManager = new BookingManager();
        flightManager = new FlightManager();

        setTitle("Airline Management System - Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Flight Management Panel
        JPanel flightPanel = new JPanel();
        flightPanel.setLayout(new BorderLayout());

        JPanel flightInputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        JTextField flightNumberField = new JTextField(20);
        JTextField flightNameField = new JTextField(20);
        JTextField sourceField = new JTextField(20);
        JTextField destinationField = new JTextField(20);
        JTextField fareField = new JTextField(20);
        JTextField availableSeatsField = new JTextField(20);
        JTextField takeOffDateField = new JTextField(20);
        JTextField takeOffTimeField = new JTextField(20);

        flightInputPanel.add(new JLabel("Flight Number:"));
        flightInputPanel.add(flightNumberField);
        flightInputPanel.add(new JLabel("Flight Name:"));
        flightInputPanel.add(flightNameField);
        flightInputPanel.add(new JLabel("Source:"));
        flightInputPanel.add(sourceField);
        flightInputPanel.add(new JLabel("Destination:"));
        flightInputPanel.add(destinationField);
        flightInputPanel.add(new JLabel("Fare:"));
        flightInputPanel.add(fareField);
        flightInputPanel.add(new JLabel("Available Seats:"));
        flightInputPanel.add(availableSeatsField);
        flightInputPanel.add(new JLabel("Take-off Date:"));
        flightInputPanel.add(takeOffDateField);
        flightInputPanel.add(new JLabel("Take-off Time:"));
        flightInputPanel.add(takeOffTimeField);

        flightPanel.add(flightInputPanel, BorderLayout.NORTH);

        flightDisplayArea = new JTextArea();
        flightDisplayArea.setEditable(false);
        JScrollPane flightScrollPane = new JScrollPane(flightDisplayArea);
        flightPanel.add(flightScrollPane, BorderLayout.CENTER);

        JButton addFlightButton = new JButton("Add Flight");
        JButton viewFlightsButton = new JButton("View All Flights");
        JButton clearFlightButton = new JButton("Clear Fields");

        JPanel flightButtonPanel = new JPanel();
        flightButtonPanel.add(addFlightButton);
        flightButtonPanel.add(viewFlightsButton);
        flightButtonPanel.add(clearFlightButton);

        flightPanel.add(flightButtonPanel, BorderLayout.SOUTH);
        tabbedPane.addTab("Flight Management", flightPanel);

        // Booking Management Panel
        JPanel bookingPanel = createBookingPanel();
        tabbedPane.addTab("Booking Management", bookingPanel);

        add(tabbedPane);

        // Action listeners for flight management
        addFlightButton.addActionListener(e -> {
            try {
                String flightNumber = flightNumberField.getText();
                String flightName = flightNameField.getText();
                String source = sourceField.getText();
                String destination = destinationField.getText();
                double fare = Double.parseDouble(fareField.getText());
                int availableSeats = Integer.parseInt(availableSeatsField.getText());
                String takeOffDate = takeOffDateField.getText();
                String takeOffTime = takeOffTimeField.getText();

                Flight flight = new Flight(flightNumber, flightName, source, destination, fare, availableSeats, takeOffDate, takeOffTime);
                flightManager.addFlight(flight);
                flightDisplayArea.setText("Flight added successfully!\n" + flight);
                updateAvailableFlights();

            } catch (NumberFormatException ex) {
                flightDisplayArea.setText("Invalid input. Please check the fare and available seats.");
            } catch (Exception ex) {
                flightDisplayArea.setText("Error: " + ex.getMessage());
            }
        });

        viewFlightsButton.addActionListener(e -> {
            StringBuilder displayText = new StringBuilder("All Flights:\n");
            for (Flight flight : flightManager.getAllFlights()) {
                displayText.append(flight).append("\n");
            }
            flightDisplayArea.setText(displayText.toString());
        });

        clearFlightButton.addActionListener(e -> {
            flightNumberField.setText("");
            flightNameField.setText("");
            sourceField.setText("");
            destinationField.setText("");
            fareField.setText("");
            availableSeatsField.setText("");
            takeOffDateField.setText("");
            takeOffTimeField.setText("");
            flightDisplayArea.setText("");
        });
    }

    private JPanel createBookingPanel() {
        JPanel bookingPanel = new JPanel();
        bookingPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));

        JTextField bookingIdField = new JTextField(20);
        JTextField flightNumberField = new JTextField(20);
        JTextField passengerNameField = new JTextField(20);
        JTextField passportNumberField = new JTextField(20);
        
        // Create a JSpinner for the number of seats
        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1); // min 1, max 10, step 1
        JSpinner numSeatsSpinner = new JSpinner(model);

        ((AbstractDocument) passportNumberField.getDocument()).setDocumentFilter(new LengthFilter(10));

        inputPanel.add(new JLabel("Booking ID:"));
        inputPanel.add(bookingIdField);
        inputPanel.add(new JLabel("Flight Number:"));
        inputPanel.add(flightNumberField);
        inputPanel.add(new JLabel("Passenger Name:"));
        inputPanel.add(passengerNameField);
        inputPanel.add(new JLabel("Passport Number:"));
        inputPanel.add(passportNumberField);
        inputPanel.add(new JLabel("Number of Seats:"));
        inputPanel.add(numSeatsSpinner); // Use the spinner instead of a text field

        bookingPanel.add(inputPanel, BorderLayout.NORTH);

        JTextArea displayArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(displayArea);
        bookingPanel.add(scrollPane, BorderLayout.CENTER);

        availableFlightsArea = new JTextArea();
        availableFlightsArea.setEditable(false);
        JScrollPane availableScrollPane = new JScrollPane(availableFlightsArea);
        bookingPanel.add(availableScrollPane, BorderLayout.EAST);

        JButton createBookingButton = new JButton("Create Booking");
        JButton cancelBookingButton = new JButton("Cancel Booking");
        JButton searchBookingButton = new JButton("Search Booking");
        JButton displayAllButton = new JButton("Display All Bookings");
        JButton viewAvailableFlightsButton = new JButton("View Available Flights");
        JButton clearBookingButton = new JButton("Clear Fields");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createBookingButton);
        buttonPanel.add(cancelBookingButton);
        buttonPanel.add(searchBookingButton);
        buttonPanel.add(displayAllButton);
        buttonPanel.add(viewAvailableFlightsButton);
        buttonPanel.add(clearBookingButton);

        bookingPanel.add(buttonPanel, BorderLayout.SOUTH);

        createBookingButton.addActionListener(e -> {
            String bookingId = bookingIdField.getText();
            String flightNumber = flightNumberField.getText();
            String passengerName = passengerNameField.getText();
            String passportNumber = passportNumberField.getText();
            try {
                int numSeats = (Integer) numSeatsSpinner.getValue(); // Get value from the spinner

                Flight flight = flightManager.searchFlight(flightNumber);
                if (flight != null && flight.getAvailableSeats() >= numSeats) {
                    double totalFare = numSeats * flight.getFare();
                    String bookingDate = "2024-10-24";

                    Booking booking = new Booking(bookingId, flightNumber, passengerName, passportNumber, totalFare, numSeats, bookingDate);
                    bookingManager.addBooking(booking);
                    flightManager.updateSeats(flightNumber, numSeats);

                    displayArea.setText(String.format(
                        "Booking created successfully!\n" +
                        "Booking ID: %s\n" +
                        "Flight Number: %s\n" +
                        "Passenger Name: %s\n" +
                        "Passport Number: %s\n" +
                        "Number of Seats: %d\n" +
                        "Total Fare: $%.2f\n" +
                        "Booking Date: %s",
                        bookingId, flightNumber, passengerName, passportNumber, numSeats, totalFare, bookingDate
                    ));
                } else {
                    displayArea.setText("Flight not found or insufficient seats.");
                }
            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid number of seats.");
            }
        });

        cancelBookingButton.addActionListener(e -> {
            String bookingId = bookingIdField.getText();
            bookingManager.cancelBooking(bookingId);
            displayArea.setText("Booking with ID " + bookingId + " cancelled.");
        });

        searchBookingButton.addActionListener(e -> {
            String bookingId = bookingIdField.getText();
            Booking booking = bookingManager.searchBooking(bookingId);
            if (booking != null) {
                displayArea.setText(booking.toString());
            } else {
                displayArea.setText("Booking not found.");
            }
        });

        displayAllButton.addActionListener(e -> {
            StringBuilder displayText = new StringBuilder("All Bookings:\n");
            for (Booking booking : bookingManager.getAllBookings()) {
                displayText.append(booking).append("\n");
            }
            displayArea.setText(displayText.toString());
        });

        viewAvailableFlightsButton.addActionListener(e -> {
            StringBuilder displayText = new StringBuilder("Available Flights:\n");
            for (Flight flight : flightManager.getAllFlights()) {
                if (flight.getAvailableSeats() > 0) {
                    displayText.append(flight).append("\n");
                }
            }
            availableFlightsArea.setText(displayText.toString());
        });

        clearBookingButton.addActionListener(e -> {
            bookingIdField.setText("");
            flightNumberField.setText("");
            passengerNameField.setText("");
            passportNumberField.setText("");
            numSeatsSpinner.setValue(1);
            displayArea.setText("");
            availableFlightsArea.setText("");
        });

        return bookingPanel;
    }

    private void updateAvailableFlights() {
        StringBuilder displayText = new StringBuilder("Available Flights:\n");
        for (Flight flight : flightManager.getAllFlights()) {
            if (flight.getAvailableSeats() > 0) {
                displayText.append(flight).append("\n");
            }
        }
        availableFlightsArea.setText(displayText.toString());
    }

    // Document filter to limit input length
    private static class LengthFilter extends DocumentFilter {
        private final int limit;

        public LengthFilter(int limit) {
            this.limit = limit;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && (fb.getDocument().getLength() + string.length() <= limit)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && (fb.getDocument().getLength() - length + text.length() <= limit)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminGUI gui = new AdminGUI();
            gui.setVisible(true);
        });
    }
}
