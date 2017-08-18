package pokemanager;

public class ItalianMessageProvider implements MessageProvider {
    public String startupMessage() {
        return "Commands:" +
                "\n'" + retrieveCommandString()  + "' per visionare i Pokemon nella memoria" +
                "\n'" + storeCommandString() + "' per depositare un Pokemon" +
                "\n'" + saveCommandString() + "' per salvare i Pokemon per la prossima volta" +
                "\n'" + speciesCommandString() + "' per ricercare nella Pokedex";
    }

    public String badCommandMessage() {
        return "Scegliere un istruzione valida.\n";
    }

    public String levelRequestMessage() {
        return "Level:";
    }

    public String speciesRequestMessage() {
        return "Specie:";
    }

    public String nicknameRequestMessage() {
        return "Nomignolo:";
    }

    public String storeSuccessMessage() {
        return "Depositato!";
    }

    public String saveSuccessMessage() {
        return "Salvato!";
    }

    public String noneFoundMessage() {
        return "Nessuna specie trovata.";
    }

    public String speciesFieldname() {
        return "Specie";
    }

    public String heightFieldname() {
        return "Altezza";
    }

    public String weightFieldname() {
        return "Peso";
    }

    public String searchMessage() {
        return "Ricercare:";
    }

    public String retrieveCommandString() {
        return "visionare";
    }

    public String storeCommandString() {
        return "depositare";
    }

    public String saveCommandString() {
        return "salvare";
    }

    public String exitCommandString() {
        return "chiudere";
    }

    public String speciesCommandString() {
        return "ricercare";
    }

    public String languageCommandString() {
        return "lingua";
    }
}
