package pokemanager;

import java.io.*;

public class UiStubSpy extends Ui{
    public boolean badInput;
    public boolean displaySpeciesCalled;
    public boolean noneFoundCalled;

    public UiStubSpy(boolean badInput) {
        super(new BufferedReader(new InputStreamReader(new ByteArrayInputStream("".getBytes()))),
                new PrintStream(new ByteArrayOutputStream()), null);
        this.badInput = badInput;
        this.displaySpeciesCalled = false;
        this.noneFoundCalled = false;
    }

    public String getSpeciesSearchInput() {
        if (badInput) {
            return "bad_input";
        } else {
            return "Bulbasaur";
        }
    }

    public void displaySpecies(Species species) {
        displaySpeciesCalled = true;
    }

    public void noneFoundMessage() {
        noneFoundCalled = true;
    }
}
