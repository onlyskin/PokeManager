package pokemanager;

public interface MessageProvider {
    String startupMessage();

    String badCommandMessage();

    String levelRequestMessage();

    String speciesRequestMessage();

    String nicknameRequestMessage();

    String storeSuccessMessage();

    String saveSuccessMessage();
}
