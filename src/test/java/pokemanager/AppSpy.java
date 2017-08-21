package pokemanager;

public class AppSpy extends App {
    public boolean exitCalled;
    
    public AppSpy() {
        super(null, null, null);
        exitCalled = false;
    }

    @Override
    public void exit() {
        exitCalled = true;
    }
}
