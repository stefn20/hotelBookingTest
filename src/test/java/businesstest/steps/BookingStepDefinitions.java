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
        addBooking();
    }

    @Then("^the booking (is|is not) displayed in the bookings list")
    public void theBookingIsOrIsNotDisplayedInTheBookingsList(String expected) {
        if (expected.equalsIgnoreCase("is")) {
            assertThat(isBookingDisplayed()).isTrue();
        } else {
            assertThat(isBookingDisplayed()).isFalse();
        }
    }

    @When("^the user deletes the booking")
    public void theUserDeletesTheBooking() {
        Booking booking = Serenity.sessionVariableCalled("booking");

        bookingsPage.open();
        bookingsPage.deleteBooking(booking);
    }

    @Given("^there is an existing booking$")
    public void thereIsAnExistingBooking() {
        addBooking();
        assertThat(isBookingDisplayed()).isTrue();
    }

    @After
    public void teardown() {
        bookingsPage.deleteAllBookingsForTearDown();
    }

    private void addBooking() {
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

    private boolean isBookingDisplayed() {
        Booking booking = Serenity.sessionVariableCalled("booking");

        bookingsPage.open();
        return bookingsPage.bookingExists(booking);
    }
}
