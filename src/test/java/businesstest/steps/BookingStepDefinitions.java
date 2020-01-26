package businesstest.steps;

import businesstest.pages.HotelBookingPage;
import cucumber.api.java.en.Given;


public class BookingStepDefinitions {

    private HotelBookingPage hotelBookingPage;

    @Given("^user is on the hotel booking page$")
    public void userIsOnTheHotelBookingPage() {
        hotelBookingPage.open();
    }
}
