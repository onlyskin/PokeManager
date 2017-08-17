package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SaveCommandTest {
	private final BoxSpy boxSpy = new BoxSpy();
    private final UiSpy uiSpy = new UiSpy();
	private SaveCommand sc = new SaveCommand(boxSpy, uiSpy);

	public SaveCommandTest() throws IOException {}

	@Test
	public void CallsSaveOnBox() throws Exception {
		sc.execute("save");
		assertTrue(boxSpy.saveCalled);
	}

	@Test
	public void CallsSaveSuccessOnUi() throws Exception {
		sc.execute("save");
        assertTrue(uiSpy.saveSuccessCalled);
	}	

	@Test
	public void RespondsToSave() throws Exception {
        Ui ui = new Ui(null, null, new MessageProviderStub());
        sc = new SaveCommand(null, ui);
		assertTrue(sc.respondsTo("save"));
	}
}	
