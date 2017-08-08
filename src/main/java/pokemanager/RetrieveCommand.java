package pokemanager;

import java.io.PrintStream;

public class RetrieveCommand {
    private final Box box;
    private PrintStream out;

    public RetrieveCommand(Box box, PrintStream out) {
        this.box = box;
        this.out = out;
    }

    public void execute() {
        String output = box.retrieve();
        out.println(output);
    }
}
