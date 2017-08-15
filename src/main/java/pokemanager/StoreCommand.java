package pokemanager;

import java.io.BufferedReader;
import java.io.IOException;

public class StoreCommand implements Command {
    public StoreCommand() {
    }

    public void execute(String command, App app) {
        BufferedReader reader = app.getReader();
        String species = "";
        String nickname = "";
        Integer level = null;
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
        app.getPrintStream().println("Level:");
        while (level == null) {
            try {
                String line = reader.readLine();
                level = Integer.parseInt(line);
            } catch (NumberFormatException|IOException e) {}
        }
        Pokemon pokemon = new Pokemon(species, nickname, level);
        app.getBox().store(pokemon);
        app.getPrintStream().println("Stored!\n");
    }

    public boolean respondsTo(String command) {
        return command.startsWith("store");
    }
}
