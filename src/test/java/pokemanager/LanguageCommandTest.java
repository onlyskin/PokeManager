package pokemanager;

import org.junit.Test;

import static org.junit.Assert.*;

public class LanguageCommandTest {
    private LanguageCommand lc;


    @Test
    public void respondsToLanguage() throws Exception {
        lc = new LanguageCommand(new Ui(null, null, new MessageProviderStub()));
        assertTrue(lc.respondsTo("language"));
    }

}
