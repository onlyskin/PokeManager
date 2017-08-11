package pokemanager;

import java.io.*;

public class SaveCommand implements Command {
	public SaveCommand() {
	}

	public void execute(String command, App app) throws IOException {
		app.getBox().save();
		app.pw.println("Saved!\n");
	}
	
	public boolean respondsTo(String command) {
		return command.startsWith("save");
	}
}
