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
        display(messageProvider.getMessage("badCommand"));
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
                          messageProvider.getMessage("onPhrase") + " " +
                          p.getDateCaught() + " " +
                          messageProvider.getMessage("atPhrase") + " " +
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
        return get(messageProvider.getMessage("dateInput"),
                dateValidator);
    }

    public Integer getLevel() {
        return get(messageProvider.getMessage("levelInput"),
                levelValidator);
    }

    public String getSpecies() {
        return get(messageProvider.getMessage("speciesInput"), nullValidator);
    }

    public String getNickname() {
        return get(messageProvider.getMessage("nicknameInput"), nullValidator);
    }

    public String getLocationCaught() {
        return get(messageProvider.getMessage("locationInput"), nullValidator);
    }

    public Integer getCurrentHp() {
        return get(messageProvider.getMessage("hpInput"), integerValidator);
    }

    public void storeSuccessMessage() {
        displayPlusSpace(messageProvider.getMessage("storedSuccess"));
    }

    public void saveSuccessMessage() {
        displayPlusSpace(messageProvider.getMessage("savedSuccess"));
    }
    
    public void displaySpecies(Species s) {
        String prettyOutput = messageProvider.getMessage("speciesField") + ": " +
            s.getSpecies()  + "\n" +
            messageProvider.getMessage("heightField") + ": " +
            String.format("%.1f", s.getHeight() / 10.0) + "m\n" +
            messageProvider.getMessage("weightField") + ": " +
            String.format("%.1f", s.getWeight() / 10.0) + "kg";
        display(prettyOutput);
    }

    public void noneFoundMessage() {
        displayPlusSpace(messageProvider.getMessage("noSpecies"));
    }

    public String getSpeciesSearchInput() {
        return get(messageProvider.getMessage("searchInput"), nullValidator);
    }

    public String getRetrieveCommandString() {
        return messageProvider.getMessage("retrieveCommand");
    }

    public String getStoreCommandString() {
        return messageProvider.getMessage("storeCommand");
    }

    public String getSaveCommandString() {
        return messageProvider.getMessage("saveCommand");
    }

    public String getExitCommandString() {
        return messageProvider.getMessage("exitCommand");
    }

    public String getSpeciesCommandString() {
        return messageProvider.getMessage("searchCommand");
    }
}
