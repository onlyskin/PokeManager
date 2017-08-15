package pokemanager;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public interface Box {
     List<Pokemon> retrieve();

     void save() throws IOException;

     void store(Pokemon pokemon);

     void store(String species, String nickname, Integer level);
}
