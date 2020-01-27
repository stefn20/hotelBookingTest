package businesstest.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Booking {

    public final String firstName;
    public final String surname;
    public final BigDecimal price;
    public final boolean depositPaid;
    public final LocalDate checkInDate;
    public final LocalDate checkOutDate;

    public Booking(String firstName, String surname, BigDecimal price, boolean depositPaid, LocalDate checkInDate, LocalDate checkOutDate) {
        this.firstName = firstName;
        this.surname = surname;
        this.price = price;
        this.depositPaid = depositPaid;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public static Booking withRandomPrice() {
        BigDecimal randomPrice = generateRandomPrice();
        LocalDate today = LocalDate.now();

        return new Booking("Marc", "Bourne", randomPrice, false, today, today.plusDays(1));
    }

    private static BigDecimal generateRandomPrice() {
        return new BigDecimal(Math.random()*1000).setScale(2, RoundingMode.FLOOR);
    }
}
