package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class FileBoxTest {
    private FileBox box;

    @Test
    public void RetrievesPokemonFromBox() throws Exception {
        makeBoxWithStringAsFile("[{\"species\":\"Charmander\",\"nickname\":\"Ember\",\"level\":12}," +
                                "{\"species\":\"Squirtle\",\"nickname\":\"Mizu\",\"level\":2}]");
        box.store(new Pokemon("Koffing", "Cloud", 20));
        box.store(new Pokemon("Lapras", "Shell", 56));
        Pokemon p0 = box.retrieve().get(0);
        assertEquals("Charmander", p0.getSpecies()); 
        assertEquals("Ember", p0.getNickname()); 
        assertEquals(new Integer(12), p0.getLevel()); 
        Pokemon p1 = box.retrieve().get(2);
        assertEquals("Koffing", p1.getSpecies()); 
        assertEquals("Cloud", p1.getNickname()); 
        assertEquals(new Integer(20), p1.getLevel()); 
    }

	@Test
    public void SavesBoxDataToFile() throws IOException {
       File tempFile = File.createTempFile("temp-", "-testfile");
       tempFile.deleteOnExit();
       FileBox box = new FileBox(tempFile.toString());
       box.store(new Pokemon("Koffing", "Cloud", 20));
       box.store(new Pokemon("Lapras", "Shell", 56));
       String beforeSave = inputStreamToString(new FileInputStream(tempFile.toString())); 
       assertEquals("", beforeSave);
       box.save();
       String afterSave = inputStreamToString(new FileInputStream(tempFile.toString())); 
       assertEquals("[{\"level\":20,\"species\":\"Koffing\",\"nickname\":\"Cloud\"}," +
                    "{\"level\":56,\"species\":\"Lapras\",\"nickname\":\"Shell\"}]", afterSave);
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
