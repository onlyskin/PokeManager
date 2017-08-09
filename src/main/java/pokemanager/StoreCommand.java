package pokemanager;

public class StoreCommand implements Command {
    public StoreCommand() {
    }

    public void execute(String command, App app) {
        String pokemon = command.substring(6);
        app.getBox().store(pokemon);
        app.pw.println("Stored!\n");
    }

    public boolean respondsTo(String command) {
        return command.startsWith("store");
    }
}
