package pokemanager;

import java.io.*;

public class Ui {
    private final PrintStream printStream;
    private final MessageProvider messageProvider;
    private final BufferedReader reader;
    private final DateValidator dateValidator;
    private final LevelValidator levelValidator;
    private final NullValidator nullValidator;
    private final IntegerValidator integerValidator;

    public Ui(BufferedReader reader, PrintStream printStream,
            String language) {
        this.printStream = printStream;
        this.reader = reader;
        this.dateValidator = new DateValidator();
        this.levelValidator = new LevelValidator();
        this.nullValidator = new NullValidator();
        this.integerValidator = new IntegerValidator();
        if (language.equals("it")) {
            this.messageProvider = new ItalianMessageProvider();
        } else {
            this.messageProvider = new EnglishMessageProvider(); 
        }
    }

    private void display(String output) {
        printStream.println(output);
    }

    private void displayPlusSpace(String output) {
        display(output);
        emptySpace();
    }

    public void startupMessage() {
       display(messageProvider.startupMessage()); 
    }

    public void badCommandMessage() {
        display(messageProvider.badCommandMessage());
    }

    public void displayOldPokemon(Pokemon p) {
        String prettyOutput = p.getNickname() + " - lv." +
                          p.getLevel().toString() + " " +
                          p.getSpecies();
        display(prettyOutput);
    }

    public void displayPokemon(Pokemon p) {
        if (p.getHeight() == null) {
            displayOldPokemon(p);
            return;
        }
        String prettyOutput = "~" + p.getNickname() + "~ lv." +
                          p.getLevel().toString() + " " +
                          p.getSpecies() + " - " +
                          String.format("%.1f", p.getHeight() / 10.0) + "m, " +
                          String.format("%.1f", p.getWeight() / 10.0) + "kg - " +
                          p.getCurrentHp().toString() + "HP - " +
                          messageProvider.caughtOnPhrase() + " " +
                          p.getDateCaught() + " " +
                          messageProvider.caughtAtPhrase() + " " +
                          p.getLocationCaught();
        display(prettyOutput);
    }

    public void emptySpace() {
        display("");
    }

    public String getInputLine() throws IOException {
        String line = reader.readLine();
        return line;
    }

    private <T> T get(String prompt, Validator<T> validator) {
        display(prompt);
        String input = null;
        try {
            input = getInputLine();
        } catch (IOException e) {}
        if (!validator.validate(input)) {
            return get(prompt, validator);
        }
        return validator.getValue(input);
    }

    public String getDateCaught() {
        return get(messageProvider.dateCaughtRequestMessage(),
                dateValidator);
    }

    public Integer getLevel() {
        return get(messageProvider.levelRequestMessage(),
                levelValidator);
    }

    public String getSpecies() {
        return get(messageProvider.speciesRequestMessage(), nullValidator);
    }

    public String getNickname() {
        return get(messageProvider.nicknameRequestMessage(), nullValidator);
    }

    public String getLocationCaught() {
        return get(messageProvider.locationCaughtRequestMessage(), nullValidator);
    }

    public Integer getCurrentHp() {
        return get(messageProvider.currentHpRequestMessage(), integerValidator);
    }

    public void storeSuccessMessage() {
        displayPlusSpace(messageProvider.storeSuccessMessage());
    }

    public void saveSuccessMessage() {
        displayPlusSpace(messageProvider.saveSuccessMessage());
    }
    
    public void displaySpecies(Species s) {
        String prettyOutput = messageProvider.speciesFieldname() + ": " +
            s.getSpecies()  + "\n" +
            messageProvider.heightFieldname() + ": " +
            String.format("%.1f", s.getHeight() / 10.0) + "m\n" +
            messageProvider.weightFieldname() + ": " +
            String.format("%.1f", s.getWeight() / 10.0) + "kg";
        display(prettyOutput);
    }

    public void noneFoundMessage() {
        displayPlusSpace(messageProvider.noneFoundMessage());
    }

    public String getSpeciesSearchInput() {
        return get(messageProvider.searchMessage(), nullValidator);
    }

    public String getRetrieveCommandString() {
        return messageProvider.retrieveCommandString();
    }

    public String getStoreCommandString() {
        return messageProvider.storeCommandString();
    }

    public String getSaveCommandString() {
        return messageProvider.saveCommandString();
    }

    public String getExitCommandString() {
        return messageProvider.exitCommandString();
    }

    public String getSpeciesCommandString() {
        return messageProvider.speciesCommandString();
    }
}
