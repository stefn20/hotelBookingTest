package businesstest.pages;

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

    public List<String> retrieveBookings() {
        List<WebElement> retrievedBookings = bookingsTable.findElements(By.cssSelector("div.row[id]"));
        return retrievedBookings.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
