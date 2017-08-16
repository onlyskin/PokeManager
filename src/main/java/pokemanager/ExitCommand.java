package pokemanager;

public class ExitCommand implements Command {
    private App app;

    public ExitCommand(App app) {
        this.app = app;
    }

    public void execute(String command) {
        app.exit();
    }

    public boolean respondsTo(String command) {
        return command.startsWith("exit");
    }
}
