package pokemanager;

import java.io.IOException;

public class StoreCommand implements Command {
    private Box box;
    private Ui ui;
    private SpeciesFinder speciesFinder;

    public StoreCommand(Box box, SpeciesFinder speciesFinder, Ui ui) {
        this.box = box;
        this.ui = ui;
        this.speciesFinder = speciesFinder;
    }

    public void execute(String command) throws IOException {
        String speciesString = ui.getSpecies();
        String nickname = ui.getNickname();
        Integer level = ui.getLevel();
        String dateCaught = ui.getDateCaught();
        String locationCaught = ui.getLocationCaught();
        Integer currentHp = ui.getCurrentHp();
        Species species = speciesFinder.findDetails(speciesString);
        Pokemon pokemon = new Pokemon(speciesString, nickname, level,
            species.getHeight(), species.getWeight(), dateCaught,
            locationCaught, currentHp);
        box.store(pokemon);
        ui.storeSuccessMessage();
    }

    public boolean respondsTo(String command) {
        return command.startsWith(ui.getStoreCommandString());
    }
}
