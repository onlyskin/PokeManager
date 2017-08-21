package pokemanager;

public class HttpGetRequesterSpy extends HttpGetRequester {
    public boolean getCalled;
    public String calledWith;

    public HttpGetRequesterSpy() {
        this.getCalled = false;
        this.calledWith = "";
    }

    @Override
    public String get(String pokemon) {
        getCalled = true;
        calledWith = pokemon;
        if (calledWith.equals("bad_input")) {
            return "{\"detail\":\"Not found.\"}";
        } else if (calledWith.equals("Charmander")) {
            return "{\"name\": \"charmander\", \"weight\": 30, \"height\": 30}";
        } else {
            return "{\"name\": \"bulbasaur\", \"weight\": 69, \"height\": 7}";
        }
    }
}
