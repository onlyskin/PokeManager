package pokemanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements StringValidator {
    public boolean validate(String input) {
        return !dateInFuture(input);
    }

    private boolean dateInFuture(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date.isAfter(LocalDate.now());
    }

}
