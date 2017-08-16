package pokemanager;

import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

public class RetrieveCommand implements Command {
    private Box box;
    private Ui ui;

    public RetrieveCommand(Box box, Ui ui) {
        this.box = box;
        this.ui = ui;
    }

    public void execute(String command) throws IOException {
        List<Pokemon> pokemon = box.retrieve();
        for (int i=0; i<pokemon.size(); i++) {
            ui.displayPokemon(pokemon.get(i));
        }
        ui.emptySpace();
    }

    public boolean respondsTo(String command) {
        return command.startsWith("box");
    }
}
