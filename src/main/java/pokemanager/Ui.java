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

    public void displayPokemon(Pokemon p) {
        String prettyOutput = p.getNickname() + " - lv." +
                              p.getLevel().toString() + " " +
                              p.getSpecies();
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
}
