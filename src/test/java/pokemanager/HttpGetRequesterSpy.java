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
        } else {
            return "{\"name\": \"bulbasaur\", \"weight\": 69, \"height\": 7}";
        }
    }
}
