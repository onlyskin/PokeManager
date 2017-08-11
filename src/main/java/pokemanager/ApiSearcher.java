package pokemanager;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONException;

public class ApiSearcher {
    private final HttpGetRequester getRequester;

    public ApiSearcher(HttpGetRequester getRequester) {
        this.getRequester = getRequester;
    }

    public Pokemon findDetails(String pokemon) throws IOException {
        String response = getRequester.get(pokemon.toLowerCase());
        JSONObject obj = new JSONObject(response);
        // try {
        //     if (obj.getString("detail").equals("Not found.")) {
        //         return null;
        //     }
        // } catch JSONException {
        // }
        String name = obj.getString("name");
        Integer height = obj.getInt("height");
        Integer weight = obj.getInt("weight");
        return new Pokemon(name, height, weight);
    }
}
