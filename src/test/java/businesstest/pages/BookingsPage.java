package businesstest.pages;

import businesstest.helper.BookingDetailsStringFormatter;
import businesstest.model.Booking;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

@DefaultUrl("/")
public class BookingsPage extends PageObject {

    private WebElementFacade firstname;
    private WebElementFacade lastname;
    private WebElementFacade totalprice;
    private WebElementFacade depositpaid;
    private WebElementFacade checkin;
    private WebElementFacade checkout;
    @FindBy(xpath = "//input[@type='button' and @value=' Save ']")
    private WebElementFacade saveButton;
    @FindBy(id = "bookings")
    private WebElementFacade bookingsTable;

    public void addBooking(String firstName, String surname, String price, boolean deposit, String checkInDate, String checkOutDate) {
        typeInto(firstname, firstName);
        typeInto(lastname, surname);
        typeInto(totalprice, price);
        depositpaid.selectByVisibleText(String.valueOf(deposit));
        typeInto(checkin, checkInDate);
        typeInto(checkout, checkOutDate);
        saveButton.click();
    }

    public boolean bookingExists(Booking expectedBooking) {
        String expectedBookingDetails = BookingDetailsStringFormatter.formatToString(expectedBooking);
        List<String> retrievedBookingDetails = retrieveBookings();

        return retrievedBookingDetails.stream().anyMatch(b -> b.equals(expectedBookingDetails));
    }

    public void deleteBooking(Booking bookingToDelete) {
        String bookingDetails = BookingDetailsStringFormatter.formatToString(bookingToDelete);
        List<WebElement> retrievedBookingWebElements = retrieveBookingWebElements();

        for (WebElement booking : retrievedBookingWebElements) {
            if (booking.getText().equals(bookingDetails)) {
                WebElement deleteButton = findDeleteButtonForBookingId(booking.getAttribute("id"));
                deleteButton.click();
            }
        }
    }

    public void deleteAllBookingsForTearDown() {
        List<WebElement> retrievedBookingWebElements = retrieveBookingWebElements();

        for (WebElement booking : retrievedBookingWebElements) {
            if (booking.getText().startsWith("Marc\nBourne")) { // Can't actually delete ALL bookings as other testers use the same environment
                WebElement deleteButton = findDeleteButtonForBookingId(booking.getAttribute("id"));
                deleteButton.click();
            }
        }
    }

    private List<String> retrieveBookings() {
        return retrieveBookingWebElements().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    private List<WebElement> retrieveBookingWebElements() {
        return bookingsTable.findElements(By.cssSelector("div.row[id]"));
    }

    private WebElement findDeleteButtonForBookingId(String bookingId) {
        return bookingsTable.findElement(By.xpath("//input[@onclick='deleteBooking(" + bookingId + ")']"));
    }
}
