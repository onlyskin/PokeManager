package pokemanager;

import java.io.IOException;
import java.io.InputStream;

public class BoxSpy extends Box {
    public boolean retrieveCalled;
    public boolean getDataStringCalled;
    public String stored;

    public BoxSpy(InputStream in) throws IOException {
        super(in);
        retrieveCalled = false;
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
        stored = pokemon;
    }
}
