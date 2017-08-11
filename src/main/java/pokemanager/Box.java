package pokemanager;

import java.io.IOException;

public interface Box {
     String retrieve();

     void save() throws IOException;

     void store(String pokemon);
}
