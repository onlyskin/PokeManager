package pokemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class StoreCommand implements Command {
    private Box box;
    private Ui ui;

    public StoreCommand(Box box, Ui ui) {
        this.box = box;
        this.ui = ui;
    }


    public void execute(String command) throws IOException {
        String species = ui.getSpecies();
        String nickname = ui.getNickname();
        Integer level = ui.getLevel();
        Pokemon pokemon = new Pokemon(species, nickname, level);
        box.store(pokemon);
        ui.storeSuccessMessage();
    }

    public boolean respondsTo(String command) {
        return command.startsWith(ui.getStoreCommandString());
    }
}
