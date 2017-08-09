package pokemanager;

import java.io.InputStream;
import java.io.PrintStream;

public class AppSpy extends App {
    public boolean exitCalled;
    
    public AppSpy(InputStream in, PrintStream pw, Box box, String storageFilename) {
        super(in, pw, box, storageFilename);
        exitCalled = false;
    }

    @Override
    public void exit() {
        exitCalled = true;
    }
}
