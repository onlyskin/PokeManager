package pokemanager;

import java.io.PrintStream;

public class StoreCommand {
    private final Box box;
    private PrintStream out;

    public StoreCommand(Box box, PrintStream out) {
        this.box = box;
        this.out = out;
    }

    public void execute(String command) {
        String pokemon = command.substring(6);
        box.store(pokemon);
        out.println("Stored!\n");
    }
}
