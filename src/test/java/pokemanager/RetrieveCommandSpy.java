package pokemanager;

import java.io.PrintStream;

public class RetrieveCommandSpy extends RetrieveCommand {
    public boolean executeCalled;

    public RetrieveCommandSpy() {
        super(null, null);
        executeCalled = false;
    }

    @Override
    public void execute(String command) {
        executeCalled = true;
    }
}
