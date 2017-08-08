package pokemanager;

import java.io.PrintStream;

public class RetrieveCommandSpy extends RetrieveCommand {
    public boolean executeCalled;

    public RetrieveCommandSpy(Box box, PrintStream out) {
        super(box, out);
        executeCalled = false;
    }

    @Override
    public void execute() {
        executeCalled = true;
    }
}
