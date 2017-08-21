package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateValidatorTest {
    private final DateValidator dv = new DateValidator();

    @Test
    public void PastDateValid() {
        assertTrue(dv.validate("18/08/2016"));
    }

    @Test
    public void FutureDateNotValid() {
        assertFalse(dv.validate("18/08/2050"));
    }
}
