package businesstest.helper;

import businesstest.model.Booking;

public class BookingDetailsStringFormatter {

    public static String formatToString(Booking booking) {
        String delimiter = "\n";

        return booking.firstName + delimiter + booking.surname + delimiter + booking.price + delimiter + booking.depositPaid + delimiter + booking.checkInDate + delimiter + booking.checkOutDate;
    }
}
