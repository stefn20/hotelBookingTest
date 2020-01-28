package businesstest.steps;

import businesstest.model.Booking;
import businesstest.pages.BookingsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingStepDefinitions {

    private static BookingsPage bookingsPage;

    @Given("^user adds a new booking")
    public void userAddsANewBooking() {
        Booking booking = Booking.withRandomPrice();
        Serenity.setSessionVariable("booking").to(booking);

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

    @Then("^the booking is displayed in the bookings list")
    public void theBookingIsDisplayedInTheBookingsList() throws InterruptedException {
        Booking booking = Serenity.sessionVariableCalled("booking");
        String bookingDetails = booking.firstName + "\n" + booking.surname + "\n" + booking.price + "\n" + booking.depositPaid + "\n" + booking.checkInDate + "\n" + booking.checkOutDate;

        bookingsPage.open();
        Thread.sleep(1000); // Horrible but not entirely sure what element on the page to wait for to avoid having to Thread.sleep()
        List<String> retrievedBookingDetails = bookingsPage.retrieveBookings();
        assertThat(retrievedBookingDetails.stream().anyMatch(b -> b.equals(bookingDetails))).isTrue();
    }
}
