import java.util.ArrayList;
import java.util.List;

public class FlightManager {
    private final List<Flight> flights;

    public FlightManager() {
        flights = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public Flight searchFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public void updateSeats(String flightNumber, int numSeats) {
        Flight flight = searchFlight(flightNumber);
        if (flight != null) {
            flight.reduceSeats(numSeats);
        }
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public List<Flight> getAvailableFlights() {
        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getAvailableSeats() > 0) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }
}
