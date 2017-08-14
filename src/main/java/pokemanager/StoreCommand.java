package pokemanager;

import java.util.List;

public class StoreCommand implements Command {
    public StoreCommand() {
    }

    public void execute(String command, App app) {
        String argString = command.substring(6);
        String[] args = argString.split(" ");
        app.getBox().store(args[0], args[1], Integer.parseInt(args[2]));
        app.pw.println("Stored!\n");
    }

    public boolean respondsTo(String command) {
        return command.startsWith("store");
    }
}
