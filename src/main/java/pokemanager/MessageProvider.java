package pokemanager;

public interface MessageProvider {
    String startupMessage();

    String badCommandMessage();

    String levelRequestMessage();

    String speciesRequestMessage();

    String nicknameRequestMessage();

    String storeSuccessMessage();

    String saveSuccessMessage();

    String noneFoundMessage();

    String speciesFieldname();

    String heightFieldname();

    String weightFieldname();

    String searchMessage();

    String retrieveCommandString();

    String storeCommandString();

    String saveCommandString();

    String exitCommandString();

    String speciesCommandString();

    String locationCaughtRequestMessage();

    String dateCaughtRequestMessage();

    String currentHpRequestMessage();
}
