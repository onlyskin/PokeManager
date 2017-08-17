package pokemanager;

import java.io.*;

public class UiSpy extends Ui {
    public boolean displayPokemonCalled;
    public boolean getLevelCalled;
    public boolean getSpeciesCalled;
    public boolean getNicknameCalled;
    public boolean storeSuccessCalled;
    public boolean saveSuccessCalled;
    public boolean displaySpeciesCalled;
    public boolean noneFoundCalled;
    public boolean getSpeciesSearchInputCalled;

    public UiSpy() {
        super(new BufferedReader(new InputStreamReader(new ByteArrayInputStream("".getBytes()))),
                new PrintStream(new ByteArrayOutputStream()), null);
        this.displayPokemonCalled = false;
        this.getLevelCalled = false;
        this.getSpeciesCalled = false;
        this.getNicknameCalled = false;
        this.storeSuccessCalled = false;
        this.saveSuccessCalled = false;
        this.displaySpeciesCalled = false;
        this.noneFoundCalled = false;
        this.getSpeciesSearchInputCalled = false;
    }

    @Override
    public void displayPokemon(Pokemon pokemon) {
        displayPokemonCalled = true;
    }

    @Override
    public Integer getLevel() {
        getLevelCalled = true;
        return null;
    }

    @Override
    public String getSpecies() {
        getSpeciesCalled = true;
        return null;
    }

    @Override
    public String getNickname() {
        getNicknameCalled = true;
        return null;
    }

    @Override
    public void storeSuccessMessage() {
        storeSuccessCalled = true;
    }

    @Override
    public void saveSuccessMessage() {
        saveSuccessCalled = true;
    }

    @Override
    public void displaySpecies(Species species) {
        displaySpeciesCalled = true;
    }

    @Override
    public void noneFoundMessage() {
        noneFoundCalled = true;
    }
    
    @Override
    public String getSpeciesSearchInput() {
        getSpeciesSearchInputCalled = true;
        return "Bulbasaur";
    }
}
