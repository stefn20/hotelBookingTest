package businesstest.steps;

import businesstest.model.Booking;
import businesstest.pages.BookingsPage;
import cucumber.api.java.en.Given;

public class BookingStepDefinitions {

    private static BookingsPage bookingsPage;

    @Given("^user adds a new booking")
    public void userAddsANewBooking() {
        Booking booking = Booking.withRandomPrice();

        bookingsPage.open();
        bookingsPage.addBooking(
                booking.firstName,
                booking.surname,
                String.valueOf(booking.price),
                booking.depositPaid,
                booking.checkInDate.toString(),
                booking.checkOutDate.toString()
        );
    }
}
