package pokemanager;

import java.io.IOException;

public class SpeciesCommand implements Command {
	private final ApiSearcher apiSearcher;

	public SpeciesCommand(ApiSearcher apiSearcher) {
		super();
		this.apiSearcher = apiSearcher;
	}

    public boolean respondsTo(String command) {
		return command.startsWith("search");
    }
    
    public void execute(String command, App app) throws IOException {
        System.out.println("execute");
        System.out.println(apiSearcher);
        String pokemonName = command.substring(7);
		Pokemon pokemon = apiSearcher.findDetails(pokemonName);
		app.pw.println(pokemon.toString());
	}

}
