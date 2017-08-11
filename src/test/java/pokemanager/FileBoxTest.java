package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;

public class FileBoxTest {
    private FileBox box;

    @Test
    public void RetrievePrintsInputStream() throws Exception {
        makeBoxWithStringAsFile("Charmander\nEmber\nSquirtle\nMizu");
        assertEquals("Ember\n(Charmander)\nMizu\n(Squirtle)\n", box.retrieve());
    }

    @Test
    public void RetrievePrintsStoredPokemon() throws Exception {
        makeBoxWithStringAsFile("Charmander\nEmber\nSquirtle\nMizu");
        box.store("Koffing Cloud");
        box.store("Lapras Shell");
        assertEquals("Ember\n(Charmander)\n" +
                     "Mizu\n(Squirtle)\n" +
                     "Cloud\n(Koffing)\n" +
                     "Shell\n(Lapras)\n", box.retrieve());
    }

    @Test
    public void SavesDataToFile() throws IOException {
       File tempFile = File.createTempFile("temp-", "-testfile");
       tempFile.deleteOnExit();
       FileBox box = new FileBox(tempFile.toString());
       box.store("Koffing Cloud");
       box.store("Lapras Shell");
       String beforeSave = inputStreamToString(new FileInputStream(tempFile.toString())); 
       assertEquals("", beforeSave);
       box.save();
       String afterSave = inputStreamToString(new FileInputStream(tempFile.toString())); 
       assertEquals("Koffing\nCloud\nLapras\nShell", afterSave);
    }

    private void makeBoxWithStringAsFile(String fileContents) throws IOException {
        File tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        FileWriter fw = new FileWriter(tempFile.toString());
        fw.write(fileContents);
        fw.close();
        box = new FileBox(tempFile.toString());
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
