package pokemanager;

import java.io.IOException;
import java.io.PrintStream;

public class SpeciesCommand implements Command {
	private SpeciesFinder speciesFinder;
    private Ui ui;

    public SpeciesCommand(SpeciesFinder speciesFinder, Ui ui) {
        super();
        this.speciesFinder = speciesFinder;
        this.ui = ui;
    }

    public boolean respondsTo(String command) {
		return command.startsWith(ui.getSpeciesCommandString());
    }
    
    public void execute(String command) throws IOException {
        String searchString = ui.getSpeciesSearchInput();
		Species species = speciesFinder.findDetails(searchString);
        if (species == null) {
            ui.noneFoundMessage();
        } else {
            ui.displaySpecies(species);
        }
    }
}
