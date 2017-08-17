package pokemanager;

import java.io.*;

public class SaveCommand implements Command {
    private Box box;
    private Ui ui;

    public SaveCommand(Box box, Ui ui) {
        this.box = box;
        this.ui = ui;
    }
    
    public void execute(String command) throws IOException {
        box.save();
        ui.saveSuccessMessage();
    }

	public boolean respondsTo(String command) {
		return command.startsWith("save");
	}
}
