package pokemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileBox implements Box {
    private final List<String> stored = new ArrayList<String>();
    private String filepath;

    public FileBox(String filepath) throws IOException {
        this.filepath = filepath;
        InputStream inputStream = new FileInputStream(filepath);
        String[] pokemonList = inputStreamToString(inputStream).split("\n");
        inputStream.close();
        for (String pokemon : pokemonList) {
            if (!pokemon.equals("")) {
                this.stored.add(pokemon);
            }
        }
    }

    public String retrieve() {
        String output = "";
        for (int i=0; i<stored.size(); i+=2) {
            output = output + stored.get(i+1) + "\n(" + stored.get(i) + ")\n";
        }
        return output;
    }

    private String getDataString() {
        return String.join("\n", stored) + "\n";
    }

    public void save() throws IOException {
		FileWriter fw = new FileWriter(filepath);
		fw.write(getDataString());
		fw.close();
    }

    public void store(String pokemon) {
        String[] fields = pokemon.split(" ");
        stored.add(fields[0]);
        stored.add(fields[1]);
    }

    private String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
