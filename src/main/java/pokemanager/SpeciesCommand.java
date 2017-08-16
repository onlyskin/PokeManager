package pokemanager;

import java.io.IOException;
import java.io.PrintStream;

public class SpeciesCommand implements Command {
	private SpeciesFinder speciesFinder;
	private PrintStream printStream;

	public SpeciesCommand(SpeciesFinder speciesFinder) {
		super();
		this.speciesFinder = speciesFinder;
	}

    public SpeciesCommand(SpeciesFinder speciesFinder, PrintStream printStream) {
        super();
        this.speciesFinder = speciesFinder;
        this.printStream = printStream;
    }

    public boolean respondsTo(String command) {
		return command.startsWith("search");
    }
    
    public void execute(String command) throws IOException {
        String name = command.substring(7);
		Species species = speciesFinder.findDetails(name);
        if (species == null) {
            printStream.println("no species found");
        } else {
		    printStream.println(species.toString());
        }
    }

    public void execute(String command, App app) throws IOException {
        String name = command.substring(7);
		Species species = speciesFinder.findDetails(name);
        if (species == null) {
            app.getPrintStream().println("no species found");
        } else {
		    app.getPrintStream().println(species.toString());
        }
	}

}
