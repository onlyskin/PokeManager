package pokemanager;

public class EnglishMessageProvider implements MessageProvider {
    public String startupMessage() {
        return "Commands:" +
                "\n'" + retrieveCommandString()  + "' to see stored Pokemon" +
                "\n'" + storeCommandString() + "' to store a Pokemon" +
                "\n'" + saveCommandString() + "' to save your stored Pokemon for next time" +
                "\n'" + speciesCommandString() + "' to search the Pokedex";
    }

    public String badCommandMessage() {
        return "Please enter a valid command.\n";
    }

    public String levelRequestMessage() {
        return "Level:";
    }

    public String speciesRequestMessage() {
        return "Species:";
    }

    public String nicknameRequestMessage() {
        return "Nickname:";
    }

    public String storeSuccessMessage() {
        return "Stored!";
    }

    public String saveSuccessMessage() {
        return "Saved!";
    }

    public String noneFoundMessage() {
        return "No species found";
    }

    public String speciesFieldname() {
        return "Species";
    }

    public String heightFieldname() {
        return "Height";
    }

    public String weightFieldname() {
        return "Weight";
    }

    public String searchMessage() {
        return "Search for:";
    }

    public String retrieveCommandString() {
        return "box";
    }

    public String storeCommandString() {
        return "store";
    }

    public String saveCommandString() {
        return "save";
    }

    public String exitCommandString() {
        return "exit";
    }

    public String speciesCommandString() {
        return "search";
    }

    public String locationCaughtRequestMessage() {
        return "Location caught";
    }

    public String dateCaughtRequestMessage() {
        return "Date caught";
    }

    public String currentHpRequestMessage() {
        return "Current HP";
    }
}
