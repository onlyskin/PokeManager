package pokemanager;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONException;

public class SpeciesFinder {
    private final HttpGetRequester getRequester;

    public SpeciesFinder(HttpGetRequester getRequester) {
        this.getRequester = getRequester;
    }

    public Species findDetails(String pokemon) throws IOException {
        String response = getRequester.get(pokemon.toLowerCase());
        JSONObject obj = new JSONObject(response);
        try {
            String name = obj.getString("name");
            Integer height = obj.getInt("height");
            Integer weight = obj.getInt("weight");
            return new Species(name, height, weight);
        } catch (JSONException e) {
            return null;
        }
    }
}
