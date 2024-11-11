import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private final List<Booking> bookings;

    public BookingManager() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void cancelBooking(String bookingId) {
        bookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
    }

    public Booking searchBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }
}
