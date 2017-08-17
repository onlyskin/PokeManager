package pokemanager;

public class ItalianMessageProvider implements MessageProvider {
    public String startupMessage() {
        return "Istruzioni:" +
                "\n'visionare' per vedere Pokemon depositati" +
                "\n'depositare' per depositare un Pokemon" +
                "\n'salvare' per savlare Pokemon depositato per la prossima volta" +
                "\n'ricercare ' per ricercare dettagli di un Pokemon";
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
}
