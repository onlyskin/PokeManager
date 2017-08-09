package pokemanager;

import java.io.PrintStream;

public class RetrieveCommandSpy extends RetrieveCommand {
    public boolean executeCalled;

    public RetrieveCommandSpy() {
        super();
        executeCalled = false;
    }

    @Override
    public void execute(String command, App app) {
        executeCalled = true;
    }
}
