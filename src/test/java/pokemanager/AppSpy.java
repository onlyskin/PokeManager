package pokemanager;

import java.io.*;

public class AppSpy extends App {
    public boolean exitCalled;
    
    public AppSpy() {
        super(new ByteArrayInputStream("".getBytes()),
                new PrintStream(new ByteArrayOutputStream()), null, null, null);
        exitCalled = false;
    }

    @Override
    public void exit() {
        exitCalled = true;
    }
}
