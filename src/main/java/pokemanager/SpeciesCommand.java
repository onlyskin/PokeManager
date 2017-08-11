package pokemanager;

import java.io.IOException;

public class SpeciesCommand implements Command {
	private final SpeciesFinder speciesFinder;

	public SpeciesCommand(SpeciesFinder speciesFinder) {
		super();
		this.speciesFinder = speciesFinder;
	}

    public boolean respondsTo(String command) {
		return command.startsWith("search");
    }
    
    public void execute(String command, App app) throws IOException {
        String name = command.substring(7);
		Species species = speciesFinder.findDetails(name);
		app.pw.println(species.toString());
	}

}
