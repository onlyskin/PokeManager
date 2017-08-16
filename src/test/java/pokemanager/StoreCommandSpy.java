package pokemanager;

import java.io.PrintStream;

public class StoreCommandSpy extends StoreCommand {
    public boolean executeCalled;

    public StoreCommandSpy() {
        super(null, null, null);
        executeCalled = false;
    }

    @Override
    public void execute(String command) {
        executeCalled = true;
    }
}
