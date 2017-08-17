package pokemanager;

public class RetrieveCommandSpy extends RetrieveCommand {
    public boolean executeCalled;

    public RetrieveCommandSpy() {
        super(null, new UiSpy());
        executeCalled = false;
    }

    @Override
    public void execute(String command) {
        executeCalled = true;
    }
}
