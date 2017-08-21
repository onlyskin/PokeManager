package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class LevelValidatorTest {
    private final LevelValidator lv = new LevelValidator();

    @Test
    public void Level0NotValid() {
        assertFalse(lv.validate(0));
    }

    @Test
    public void Level50Valid() {
        assertTrue(lv.validate(50));
    }

    @Test
    public void Level100NotValid() {
        assertFalse(lv.validate(100));
    }
}
