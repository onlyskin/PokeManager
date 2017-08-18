package pokemanager;

import java.io.*;

public class Ui {
    private final PrintStream printStream;
    private final MessageProvider messageProvider;
    private final BufferedReader reader;

    public Ui(BufferedReader reader, PrintStream printStream,
            MessageProvider messageProvider) {
        this.printStream = printStream;
        this.messageProvider = messageProvider;
        this.reader = reader;
    }

    private void display(String output) {
        printStream.println(output);
    }

    public void startupMessage() {
       display(messageProvider.startupMessage()); 
    }

    public void badCommandMessage() {
        display(messageProvider.badCommandMessage());
    }

    public void displayOldPokemon(Pokemon p) {
        String prettyOutput = p.getNickname() + " - lv." +
                          p.getLevel().toString() + " " +
                          p.getSpecies();
        display(prettyOutput);
    }

    public void displayPokemon(Pokemon p) {
        if (p.getHeight() == null) {
            displayOldPokemon(p);
            return;
        }
        String prettyOutput = p.getNickname() + " - lv." +
                          p.getLevel().toString() + " " +
                          p.getSpecies() + ", " +
                          String.format("%.1f", p.getHeight() / 10.0) + "m, " +
                          String.format("%.1f", p.getWeight() / 10.0) + "kg";
        display(prettyOutput);
    }

    public void emptySpace() {
        display("");
    }

    public String getInputLine() throws IOException {
        String line = reader.readLine();
        return line;
    }

    public Integer getLevel() {
        display(messageProvider.levelRequestMessage());
        Integer level = null;
        String input = null;
        try {
            input = getInputLine();
        } catch (IOException e) {}
        try {
            level = Integer.parseInt(input);
            assert 1 <= level && level <= 99;
        } catch (NumberFormatException|AssertionError e) {
            return getLevel();
        }
        return level;
    }

    public String getSpecies() {
        display(messageProvider.speciesRequestMessage());
        String species = null;
        try {
            species = getInputLine();
        } catch (IOException e) {}
        return species;
    }

    public String getNickname() {
        display(messageProvider.nicknameRequestMessage());
        String nickname = null;
        try {
            nickname = getInputLine();
        } catch (IOException e) {}
        return nickname;
    }

    public void storeSuccessMessage() {
        display(messageProvider.storeSuccessMessage());
        emptySpace();
    }

    public void saveSuccessMessage() {
        display(messageProvider.saveSuccessMessage());
        emptySpace();
    }
    
    public void displaySpecies(Species s) {
        String prettyOutput = messageProvider.speciesFieldname() + ": " +
            s.getSpecies()  + "\n" +
            messageProvider.heightFieldname() + ": " +
            s.getHeight()  + "\n" +
            messageProvider.weightFieldname() + ": " +
            s.getWeight();
        display(prettyOutput);
    }

    public void noneFoundMessage() {
        display(messageProvider.noneFoundMessage());
        emptySpace();
    }

    public String getSpeciesSearchInput() {
        display(messageProvider.searchMessage());
        String searchString = null;
        try {
            searchString = getInputLine();
        } catch (IOException e) {}
        return searchString;
    }

    public String getRetrieveCommandString() {
        return messageProvider.retrieveCommandString();
    }

    public String getStoreCommandString() {
        return messageProvider.storeCommandString();
    }

    public String getSaveCommandString() {
        return messageProvider.saveCommandString();
    }

    public String getExitCommandString() {
        return messageProvider.exitCommandString();
    }

    public String getSpeciesCommandString() {
        return messageProvider.speciesCommandString();
    }

    public String getLanguageCommandString() {
        return messageProvider.languageCommandString();
    }
}
