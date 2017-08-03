package pokemanager;

import java.io.InputStream;

public class BoxSpy extends Box {
    public boolean retrieveCalled;
    public String stored;

    public BoxSpy(InputStream in) {
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
