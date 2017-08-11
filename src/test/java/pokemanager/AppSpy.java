package pokemanager;

import java.io.InputStream;
import java.io.PrintStream;

public class AppSpy extends App {
    public boolean exitCalled;
    
    public AppSpy(InputStream in,
                  PrintStream pw,
                  Box box,
                  HttpGetRequester getRequester) {
        super(in, pw, box, getRequester);
        exitCalled = false;
    }

    @Override
    public void exit() {
        exitCalled = true;
    }
}
