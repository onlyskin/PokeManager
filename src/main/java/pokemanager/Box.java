package pokemanager;

import java.io.IOException;

public interface Box {
     String retrieve();

     void save() throws IOException;

     void store(Pokemon pokemon);

     void store(String species, String nickname, Integer level);
}
