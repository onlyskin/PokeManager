package pokemanager;

public class LanguageCommand implements Command {
    private Ui ui;

    public LanguageCommand(Ui ui) {
        this.ui = ui;
    }

    public void execute(String command) {
    }

    public boolean respondsTo(String command) {
        return command.startsWith(ui.getLanguageCommandString());
    } 
}
