package pokemanager;

public class MessageProviderStub implements MessageProvider {
    public MessageProviderStub() {}

    public String startupMessage() {
        return "startup message";
    }
    
    public String badCommandMessage() {
        return "bad command message\n";
    }
}
