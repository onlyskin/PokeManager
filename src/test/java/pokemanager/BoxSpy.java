package pokemanager;

import java.io.IOException;
import java.io.InputStream;

public class BoxSpy implements Box {
    public boolean retrieveCalled;
    public boolean storeCalled;
    public boolean saveCalled;
    public String stored;

    public BoxSpy() throws IOException {
        retrieveCalled = false;
        storeCalled = false;
        saveCalled = false;
    }

    @Override
    public String retrieve() {
        retrieveCalled = true;
        return "Bulbasaur\nHana";
    }

    @Override
    public void store(String pokemon) {
        storeCalled = true;
        stored = pokemon;
    }

    @Override
    public void save() {
        saveCalled = true;
    }
}
