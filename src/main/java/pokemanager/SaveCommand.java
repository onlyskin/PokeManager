package pokemanager;

import java.io.*;

public class SaveCommand implements Command {
	public SaveCommand() {
	}

	public void execute(String command, App app) throws IOException {
		String contents = app.getBox().getDataString();
		FileWriter fw = new FileWriter(app.getStoragePath());
		fw.write(contents);
		fw.close();
		app.pw.println("Saved!\n");
	}
	
	public boolean respondsTo(String command) {
		return command.startsWith("save");
	}
}
