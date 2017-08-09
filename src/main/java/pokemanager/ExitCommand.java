package pokemanager;

public class ExitCommand implements Command {
    public ExitCommand() {}

    public void execute(String command, App app) {
        app.exit();
    }

    public boolean respondsTo(String command) {
        return command.startsWith("exit");
    }
}
