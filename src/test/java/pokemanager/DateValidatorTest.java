package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;

public class DateValidatorTest {
    @Test
    public void PastDateValid() {
        DateValidator dv = new DateValidator();
        assertTrue(dv.validate("18/08/2016"));
    }

    @Test
    public void FutureDateNotValid() {
        DateValidator dv = new DateValidator();
        assertFalse(dv.validate("18/08/2050"));
    }
}
