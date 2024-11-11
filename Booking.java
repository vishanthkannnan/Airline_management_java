public class Booking {
    private String bookingId;
    private String flightNumber;
    private String passengerName;
    private String passportNumber;
    private double totalFare;
    private int numSeats;
    private String bookingDate;

    public Booking(String bookingId, String flightNumber, String passengerName, 
                   String passportNumber, double totalFare, int numSeats, String bookingDate) {
        this.bookingId = bookingId;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.passportNumber = passportNumber;
        this.totalFare = totalFare;
        this.numSeats = numSeats;
        this.bookingDate = bookingDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    @Override
    public String toString() {
        return String.format("Booking ID: %s, Flight: %s, Passenger: %s, Total Fare: $%.2f",
                             bookingId, flightNumber, passengerName, totalFare);
    }
}
