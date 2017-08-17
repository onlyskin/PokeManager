package pokemanager;

public class StoreCommandSpy extends StoreCommand {
    public boolean executeCalled;

    public StoreCommandSpy() {
        super(null, null);
        executeCalled = false;
    }

    @Override
    public void execute(String command) {
        executeCalled = true;
    }
}
