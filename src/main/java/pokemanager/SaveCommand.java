package pokemanager;

import java.io.*;

public class SaveCommand implements Command {
    private Box box;
    private PrintStream printStream;

    public SaveCommand(Box box, PrintStream printStream) {
        this.box = box;
        this.printStream = printStream;
    }
    
    public void execute(String command) throws IOException {
        box.save();
        printStream.println("Saved!\n");
    }

	public boolean respondsTo(String command) {
		return command.startsWith("save");
	}
}
