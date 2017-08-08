package pokemanager;

import java.io.PrintStream;

public class StoreCommandSpy extends StoreCommand {
    public boolean executeCalled;

    public StoreCommandSpy(Box box, PrintStream out) {
        super(box, out);
        executeCalled = false;
    }

    @Override
    public void execute(String command) {
        executeCalled = true;
    }
}
