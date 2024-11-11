public class Flight {
    private String flightNumber;
    private String flightName;
    private String source;
    private String destination;
    private double fare;
    private int availableSeats;
    private String takeOffDate;
    private String takeOffTime;

    public Flight(String flightNumber, String flightName, String source, String destination, 
                  double fare, int availableSeats, String takeOffDate, String takeOffTime) {
        this.flightNumber = flightNumber;
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.fare = fare;
        this.availableSeats = availableSeats;
        this.takeOffDate = takeOffDate;
        this.takeOffTime = takeOffTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public double getFare() {
        return fare;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void reduceSeats(int num) {
        this.availableSeats -= num;
    }

    @Override
    public String toString() {
        return String.format("Flight Number: %s, Flight Name: %s, Source: %s, Destination: %s, Fare: $%.2f, Available Seats: %d, Date: %s, Time: %s",
                             flightNumber, flightName, source, destination, fare, availableSeats, takeOffDate, takeOffTime);
    }
}
