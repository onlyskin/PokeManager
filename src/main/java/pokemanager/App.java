package pokemanager;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class App {
    private PrintStream printStream;
    private BufferedReader reader;
    private Box box;
    private String storageFilename;
    private List<Command> commands;
    private boolean run;
    private SpeciesFinder speciesFinder;
    private HttpGetRequester getRequester;
    private Ui ui;

    public App(InputStream in,
               PrintStream printStream,
               Box box,
               HttpGetRequester getRequester,
               Ui ui) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.printStream = printStream;
        this.box = box;
        this.speciesFinder = new SpeciesFinder(getRequester);
        this.run = false;
        this.getRequester = getRequester;
        this.commands = buildCommands(this);
        this.ui = ui;
    }

    private List<Command> buildCommands(App app) {
        return Arrays.asList(
                new RetrieveCommand(app.box, app.printStream),
                new StoreCommand(app.box, app.reader, app.printStream),
                new SaveCommand(app.box, app.printStream),
                new ExitCommand(app),
                new SpeciesCommand(speciesFinder, app.printStream));
    }

    private Command findCommand(String command) {
        for (Command c : commands) {
            if (c.respondsTo(command)) {
                return c;
            }
        }
        return null;
    }

	public Box getBox() {
		return box;	
	}
	
    public BufferedReader getReader() {
        return reader;
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

	public void acceptInput() {
        try {
            String line = reader.readLine();
            handleInputLine(line);
        } catch (Exception IOException) {}
    }

    private void outputStartMessage() {
        ui.startupMessage();
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
            printStream.println("Please enter a valid command.\n");
        } else {
            command.execute(line);
        }
    }

    public static void main(String[] args) throws IOException {
        String filepath = "/Users/sam/Documents/pokemanager/data/data";
        FileBox box = new FileBox(filepath);
        PrintStream printStream = System.out;
        HttpGetRequester getRequester = new HttpGetRequester();
        Ui ui = new Ui(new BufferedReader(new InputStreamReader(System.in)),
                    printStream, new EnglishMessageProvider());
        App app = new App(System.in,
                          printStream,
                          box,
                          getRequester,
                          ui);
        app.run();
    }
}
