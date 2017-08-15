package pokemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class StoreCommand implements Command {
    public StoreCommand() {
    }

    public void execute(String command, App app) {
        BufferedReader reader = app.getReader();
        PrintStream printStream = app.getPrintStream();
        String species = "";
        String nickname = "";
        app.getPrintStream().println("Species:");
        while (species == "") {
            try {
                species = reader.readLine();
            } catch (IOException e) {}
        }
        app.getPrintStream().println("Nickname:");
        while (nickname == "") {
            try {
                nickname = reader.readLine();
            } catch (IOException e) {}
        }
        Integer level = getLevel(reader, printStream);
        Pokemon pokemon = new Pokemon(species, nickname, level);
        app.getBox().store(pokemon);
        app.getPrintStream().println("Stored!\n");
    }

    private Integer getLevel(BufferedReader reader, PrintStream printStream) {
        Integer level = null;
        printStream.println("Level:");
        while (level == null) {
            try {
                String line = reader.readLine();
                level = Integer.parseInt(line);
                if (level > 99) {
                    level = null;
                    printStream.println("Level:");
                }
            } catch (NumberFormatException|IOException e) {}
        }
        return level;
    }
    
    public boolean respondsTo(String command) {
        return command.startsWith("store");
    }
}
