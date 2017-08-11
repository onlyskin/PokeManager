package pokemanager;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class App {
    private BufferedReader reader;
    public PrintStream pw;
    private Box box;
    private String storageFilename;
    private List<Command> commands;
    private boolean run;
    private SpeciesFinder speciesFinder;
    private HttpGetRequester getRequester;

    public App(InputStream in,
               PrintStream pw,
               Box box,
               String storageFilename,
               HttpGetRequester getRequester) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.pw = pw;
        this.box = box;
        this.storageFilename = storageFilename;
        this.speciesFinder = new SpeciesFinder(getRequester);
        this.commands = buildCommands();
        this.run = false;
        this.getRequester = getRequester;
    }

    private List<Command> buildCommands() {
        return Arrays.asList(
                new RetrieveCommand(),
                new StoreCommand(),
                new SaveCommand(),
                new ExitCommand(),
                new SpeciesCommand(speciesFinder));
    }

    private Command findCommand(String command) {
        for (Command c : commands) {
            if (c.respondsTo(command)) {
                return c;
            }
        }
        return null;
    }

	public String getStoragePath() {
		return storageFilename;
	}

	public Box getBox() {
		return box;	
	}
	
	public void acceptInput() {
        try {
            String line = reader.readLine();
            handleInputLine(line);
        } catch (Exception IOException) {}
    }

    private void outputStartMessage() {
        pw.println("Commands:\n'box' to see stored Pokemon" +
                "\n'store SPECIES NICKNAME' to store a Pokemon" +
                "\n'save' to save your stored Pokemon for next time" +
                "\n'search SPECIES' to search the Pokedex");
    }

    public void run() {
        run = true;
        outputStartMessage();
        while (run) {
            acceptInput();
        }
    }

    public void exit() {
        run = false;
    }

    private void handleInputLine(String line) throws IOException {
        Command command = findCommand(line);
        if (command == null) {
            pw.println("Please enter a valid command.\n");
        } else {
            command.execute(line, this);
        }
    }

    public static void main(String[] args) throws IOException {
        String filepath = "/Users/sam/Documents/pokemanager/data/data";
        FileBox box = new FileBox(filepath);
        PrintStream pw = System.out;
        HttpGetRequester getRequester = new HttpGetRequester();
        App app = new App(System.in,
                          pw,
                          box,
                          filepath,
                          getRequester);
        app.run();
    }
}
