package pokemanager;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class App {
    private Box box;
    private List<Command> commands;
    private boolean run;
    private SpeciesFinder speciesFinder;
    private Ui ui;

    public App(Box box,
               HttpGetRequester getRequester,
               Ui ui) {
        this.box = box;
        this.speciesFinder = new SpeciesFinder(getRequester);
        this.run = false;
        this.ui = ui;
        this.commands = buildCommands(this);
    }

    private List<Command> buildCommands(App app) {
        return Arrays.asList(
                new RetrieveCommand(app.box, app.ui),
                new StoreCommand(app.box, speciesFinder, app.ui),
                new SaveCommand(app.box, app.ui),
                new ExitCommand(app, app.ui),
                new SpeciesCommand(speciesFinder, app.ui));
    }

    private Command findCommand(String command) {
        for (Command c : commands) {
            if (c.respondsTo(command)) {
                return c;
            }
        }
        return null;
    }

    public void run() {
        run = true;
        ui.startupMessage();
        while (run) {
            acceptInput();
        }
    }

    public void exit() {
        run = false;
    }

    private void acceptInput() {
        try {
            String line = ui.getInputLine();
            handleInputLine(line);
        } catch (Exception IOException) {}
    }

    private void handleInputLine(String line) throws IOException {
        Command command = findCommand(line);
        if (command == null) {
            ui.badCommandMessage();
        } else {
            command.execute(line);
        }
    }

    public static void main(String[] args) throws IOException {
        String language = args[0];
        String filepath = "/Users/sam/Documents/pokemanager/data/data";
        FileBox box = new FileBox(filepath);
        HttpGetRequester getRequester = new HttpGetRequester();
        Ui ui = new Ui(new BufferedReader(new InputStreamReader(System.in)),
                    System.out, language);
        App app = new App(box,
                          getRequester,
                          ui);
        app.run();
    }
}
