package pokemanager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.json.JSONObject.valueToString;

public class FileBox implements Box {
    private final List<Pokemon> stored = new ArrayList<Pokemon>();
    private String filepath;

    public FileBox(String filepath) throws IOException {
        this.filepath = filepath;
        InputStream inputStream = new FileInputStream(filepath);
        String jsonString = inputStreamToString(inputStream);
        jsonToPokemonList(jsonString);
        inputStream.close();
    }

    private void jsonToPokemonList(String jsonString) {
        JSONArray arr;
        try {
            arr = new JSONArray(jsonString);
        } catch (JSONException e) {
            arr = new JSONArray("[]");
        }
        for (int i=0; i<arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Pokemon pokemon = new Pokemon(obj.getString("species"),
                                          obj.getString("nickname"),
                                          obj.getInt("level"));
            this.stored.add(pokemon);
        }
    }

    public List<Pokemon> retrieve() {
        return stored;
    }

    private String getDataString() {
        return valueToString(stored);
    }

    public void save() throws IOException {
		FileWriter fw = new FileWriter(filepath);
		fw.write(getDataString());
		fw.close();
    }

    public void store(Pokemon pokemon) {
        stored.add(pokemon);
    }

    public void store(String species, String nickname, Integer level) {
        Pokemon p = new Pokemon(species, nickname, level);
        stored.add(p);
    }

    private String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
