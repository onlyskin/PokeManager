package pokemanager;

public class RetrieveCommand implements Command {
    public RetrieveCommand() {
    }

    public void execute(String command, App app) {
        String output = app.getBox().retrieve();
        app.pw.println(output);
    }

    public boolean respondsTo(String command) {
        return command.startsWith("box");
    }
}
