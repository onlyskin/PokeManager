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

    public List<Pokemon> retrieve() {
        return stored;
    }

    public void store(Pokemon pokemon) {
        stored.add(pokemon);
    }

    public void save() throws IOException {
		FileWriter fw = new FileWriter(filepath);
		fw.write(getDataString());
		fw.close();
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
                                          obj.getInt("level"),
                                          obj.getInt("height"),
                                          obj.getInt("weight"),
                                          obj.getString("dateCaught"),
                                          obj.getString("locationCaught"),
                                          obj.getInt("currentHp"));
            this.stored.add(pokemon);
        }
    }

    private String getDataString() {
        return valueToString(stored);
    }

    private String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
