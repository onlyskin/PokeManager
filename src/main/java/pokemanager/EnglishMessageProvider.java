package pokemanager;

public class EnglishMessageProvider implements MessageProvider {
    public String startupMessage() {
        return "Commands:" +
                "\n'box' to see stored Pokemon" +
                "\n'store to store a Pokemon" +
                "\n'save' to save your stored Pokemon for next time" +
                "\n'search SPECIES' to search the Pokedex";
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
}
