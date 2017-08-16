package pokemanager;

import java.io.PrintStream;
import java.io.IOException;
import java.util.List;

public class RetrieveCommand implements Command {
    private Box box;
    private PrintStream printStream;

    public RetrieveCommand(Box box, PrintStream printStream) {
        this.box = box;
        this.printStream = printStream;
    }
    
    public void execute(String command) throws IOException {
        List<Pokemon> pokemon = box.retrieve();
        for (int i=0; i<pokemon.size(); i++) {
            printStream.println(pokemon.get(i).prettyString());
        }
        printStream.println("");
    }

    public void execute(String command, App app) throws IOException {
        List<Pokemon> pokemon = app.getBox().retrieve();
        for (int i=0; i<pokemon.size(); i++) {
            printStream.println(pokemon.get(i).prettyString());
        }
        printStream.println("");
    }

    public boolean respondsTo(String command) {
        return command.startsWith("box");
    }
}
