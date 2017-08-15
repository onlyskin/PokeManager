package pokemanager;

import java.io.PrintStream;
import java.util.List;

public class RetrieveCommand implements Command {
    public RetrieveCommand() {
    }

    public void execute(String command, App app) {
        List<Pokemon> pokemon = app.getBox().retrieve();
        PrintStream ps = app.getPrintStream();
        for (int i=0; i<pokemon.size(); i++) {
            ps.println(pokemon.get(i).prettyString());
        }
        ps.println("");
    }

    public boolean respondsTo(String command) {
        return command.startsWith("box");
    }
}
