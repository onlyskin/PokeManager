package pokemanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Box {
    private final InputStream in;
    private final List<String> stored = new ArrayList<String>();

    public Box(InputStream in) throws IOException {
        this.in = in;
        String[] pokemonList = inputStreamToString(in).split("\n");
        in.close();
        for (String pokemon : pokemonList) {
            this.stored.add(pokemon);
        }
    }

    public String retrieve() {
        return String.join("\n", stored);
    }

    public void store(String pokemon) {
        stored.add(pokemon);
    }

    private String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
