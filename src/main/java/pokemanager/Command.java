package pokemanager;

import java.io.IOException;

public interface Command {
    void execute(String command) throws IOException;

    void execute(String command, App app) throws IOException;

    boolean respondsTo(String command);
}
