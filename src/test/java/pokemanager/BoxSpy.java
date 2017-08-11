package pokemanager;

import java.io.IOException;
import java.io.InputStream;

public class BoxSpy implements Box {
    public boolean retrieveCalled;
    public boolean storeCalled;
    public boolean getDataStringCalled;
    public String stored;

    public BoxSpy() throws IOException {
        retrieveCalled = false;
        storeCalled = false;
    }

    @Override
    public String retrieve() {
        retrieveCalled = true;
        return "Bulbasaur\nHana";
    }

    @Override
    public String getDataString() {
        getDataStringCalled = true;
        return "Bulbasaur\nHana";
    }

    @Override
    public void store(String pokemon) {
        storeCalled = true;
        stored = pokemon;
    }
}
