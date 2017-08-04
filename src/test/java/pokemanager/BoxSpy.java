package pokemanager;

import java.io.IOException;
import java.io.InputStream;

public class BoxSpy extends Box {
    public boolean retrieveCalled;
    public String stored;

    public BoxSpy(InputStream in) throws IOException {
        super(in);
        retrieveCalled = false;
    }

    @Override
    public String retrieve() {
        retrieveCalled = true;
        return "Bulbasaur";
    }

    @Override
    public void store(String pokemon) {
        stored = pokemon;
    }
}
