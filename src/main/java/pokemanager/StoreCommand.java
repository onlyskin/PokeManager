package pokemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class StoreCommand implements Command {
    public StoreCommand() {
    }

    public void execute(String command) throws IOException { return; }

    public void execute(String command, App app) {
        BufferedReader reader = app.getReader();
        PrintStream printStream = app.getPrintStream();
        String species = "";
        String nickname = "";
        Integer level = null;
        try {
            species = getInputString(reader, printStream, "Species:");
            nickname = getInputString(reader, printStream, "Nickname:");
            level = getInputLevel(reader, printStream);
        } catch (IOException e) {}
        Pokemon pokemon = new Pokemon(species, nickname, level);
        app.getBox().store(pokemon);
        app.getPrintStream().println("Stored!\n");
    }

    private String getInput(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        return line;
    }
    
    private String getInputString(BufferedReader reader,
            PrintStream printStream, String message) throws IOException {
        printStream.println(message);
        String userInput = getInput(reader);
        try {
            assert userInput != "";
            return userInput;
        } catch (AssertionError e) {
            return getInputString(reader, printStream, message);
        }
    }

    private Integer getInputLevel(BufferedReader reader,
            PrintStream printStream) throws IOException {
        printStream.println("Level:");
        String userInput = getInput(reader);
        try {
            Integer output = Integer.parseInt(userInput);
            assert output <= 99;
            return output;
        } catch (NumberFormatException|AssertionError e) {
            return getInputLevel(reader, printStream);
        }
    }

    public boolean respondsTo(String command) {
        return command.startsWith("store");
    }
}
