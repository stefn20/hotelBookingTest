package businesstest.steps;

import businesstest.model.Booking;
import businesstest.pages.BookingsPage;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

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

    @Then("^the booking (is|is not) displayed in the bookings list")
    public void theBookingIsDisplayedInTheBookingsList(String expected) throws InterruptedException {
        Booking booking = Serenity.sessionVariableCalled("booking");

        bookingsPage.open();
        Thread.sleep(1000); // Horrible but not entirely sure what element on the page to wait for to avoid having to Thread.sleep()
        if (expected.equalsIgnoreCase("is")) {
            assertThat(bookingsPage.bookingExists(booking)).isTrue();
        } else {
            assertThat(bookingsPage.bookingExists(booking)).isFalse();
        }
    }

    @When("^the user deletes the booking")
    public void theUserDeletesTheBooking() throws InterruptedException {
        Booking booking = Serenity.sessionVariableCalled("booking");

        bookingsPage.open();
        Thread.sleep(1000); // Horrible but not entirely sure what element on the page to wait for to avoid having to Thread.sleep()
        bookingsPage.deleteBooking(booking);
    }

    @After
    public void teardown() {
        bookingsPage.deleteAllBookingsForTearDown();
    }
}
