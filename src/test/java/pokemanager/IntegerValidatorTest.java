package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerValidatorTest {
    private final IntegerValidator iv = new IntegerValidator();

    @Test
    public void UnparseableNotValid() {
        assertFalse(iv.validate("three"));
    }

    @Test
    public void ParseableValid() {
        assertTrue(iv.validate("50"));
    }

    @Test
    public void GetsValue() {
        assertEquals(new Integer(50), iv.getValue("50"));
    }
}
