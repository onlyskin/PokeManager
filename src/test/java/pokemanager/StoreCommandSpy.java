package pokemanager;

import java.io.PrintStream;

public class StoreCommandSpy extends StoreCommand {
    public boolean executeCalled;

    public StoreCommandSpy() {
        super();
        executeCalled = false;
    }

    @Override
    public void execute(String command, App app) {
        executeCalled = true;
    }
}
