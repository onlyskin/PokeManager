package pokemanager;

public class BoxSpy extends Box {
    public boolean retrieveCalled;
    public String stored;

    public BoxSpy() {
        super();
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
