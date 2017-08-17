package pokemanager;

public class MessageProviderStub implements MessageProvider {
    public MessageProviderStub() {}

    public String startupMessage() {
        return "startup message";
    }
    
    public String badCommandMessage() {
        return "bad command message\n";
    }

    public String levelRequestMessage() {
    	return "level request message";
    }

    public String speciesRequestMessage() {
    	return "species request message";
    }

    public String nicknameRequestMessage() {
    	return "nickname request message";
    }

    public String storeSuccessMessage() {
        return "store success message";
    }

    public String saveSuccessMessage() {
        return "save success message";
    }

    public String noneFoundMessage() {
        return "none found message";
    }

    public String speciesFieldname() {
        return "species fieldname";
    }

    public String heightFieldname() {
        return "height fieldname";
    }

    public String weightFieldname() {
        return "weight fieldname";
    }

    public String searchMessage() {
        return "search message";
    }
}
