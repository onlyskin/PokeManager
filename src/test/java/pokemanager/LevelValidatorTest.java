package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;

public class LevelValidatorTest {
    @Test
    public void Level0NotValid() {
        LevelValidator lv = new LevelValidator();
        assertFalse(lv.validate(0));
    }

    @Test
    public void Level50Valid() {
        LevelValidator lv = new LevelValidator();
        assertTrue(lv.validate(50));
    }

    @Test
    public void Level100NotValid() {
        LevelValidator lv = new LevelValidator();
        assertFalse(lv.validate(100));
    }
}
