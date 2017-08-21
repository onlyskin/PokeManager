package pokemanager;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Ui {
    private final PrintStream printStream;
    private MessageProvider messageProvider;
    private final BufferedReader reader;

    public Ui(BufferedReader reader, PrintStream printStream,
            String language) {
        this.printStream = printStream;
        this.reader = reader;
        if (language.equals("it")) {
            this.messageProvider = new ItalianMessageProvider();
        } else {
            this.messageProvider = new EnglishMessageProvider(); 
        }
    }

    private void display(String output) {
        printStream.println(output);
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
                          p.getCurrentHp().toString() + "HP - caught on " +
                          p.getDateCaught() + " at " + p.getLocationCaught();
        display(prettyOutput);
    }

    public void emptySpace() {
        display("");
    }

    public String getInputLine() throws IOException {
        String line = reader.readLine();
        return line;
    }

    public Integer getLevel() {
        display(messageProvider.levelRequestMessage());
        Integer level = null;
        String input = null;
        try {
            input = getInputLine();
        } catch (IOException e) {}
        try {
            level = Integer.parseInt(input);
            if (level <= 0 || level > 99) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            return getLevel();
        }
        return level;
    }

    public String getSpecies() {
        display(messageProvider.speciesRequestMessage());
        String species = null;
        try {
            species = getInputLine();
        } catch (IOException e) {}
        return species;
    }

    public String getNickname() {
        display(messageProvider.nicknameRequestMessage());
        String nickname = null;
        try {
            nickname = getInputLine();
        } catch (IOException e) {}
        return nickname;
    }

    public String getLocationCaught() {
        display(messageProvider.locationCaughtRequestMessage());
        String locationCaught = null;
        try {
            locationCaught = getInputLine();
        } catch (IOException e) {}
        return locationCaught;
    }

    public Integer getCurrentHp() {
        display(messageProvider.currentHpRequestMessage());
        Integer level = null;
        String input = null;
        try {
            input = getInputLine();
        } catch (IOException e) {}
        try {
            level = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return getLevel();
        }
        return level;
    }

    private boolean dateInFuture(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date.isAfter(LocalDate.now());
    }

    public String getDateCaught() {
        display(messageProvider.dateCaughtRequestMessage());
        String dateCaught = null;
        try {
            dateCaught = getInputLine();
        } catch (IOException e) {}
        if (dateInFuture(dateCaught)) {
            return getDateCaught();
        };
        return dateCaught;
    }

    public void storeSuccessMessage() {
        display(messageProvider.storeSuccessMessage());
        emptySpace();
    }

    public void saveSuccessMessage() {
        display(messageProvider.saveSuccessMessage());
        emptySpace();
    }
    
    public void displaySpecies(Species s) {
        String prettyOutput = messageProvider.speciesFieldname() + ": " +
            s.getSpecies()  + "\n" +
            messageProvider.heightFieldname() + ": " +
            s.getHeight()  + "\n" +
            messageProvider.weightFieldname() + ": " +
            s.getWeight();
        display(prettyOutput);
    }

    public void noneFoundMessage() {
        display(messageProvider.noneFoundMessage());
        emptySpace();
    }

    public String getSpeciesSearchInput() {
        display(messageProvider.searchMessage());
        String searchString = null;
        try {
            searchString = getInputLine();
        } catch (IOException e) {}
        return searchString;
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

    public String getLanguageCommandString() {
        return messageProvider.languageCommandString();
    }
}
