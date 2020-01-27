package businesstest.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;

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

    public void addBooking(String firstName, String surname, String price, boolean deposit, String checkInDate, String checkOutDate) {
        typeInto(firstname, firstName);
        typeInto(lastname, surname);
        typeInto(totalprice, price);
        depositpaid.selectByVisibleText(String.valueOf(deposit));
        typeInto(checkin, checkInDate);
        typeInto(checkout, checkOutDate);
        saveButton.click();
    }
}
