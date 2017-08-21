package pokemanager;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;

public class FileBoxTest {
    private FileBox box;

    @Test
    public void RetrievesPokemonFromBox() throws Exception {
        box = makeBoxWithStringAsFile("[{\"species\":\"Charmander\"," +
                "\"nickname\":\"Ember\",\"level\":12,\"height\":30," +
                "\"weight\":30,\"dateCaught\":\"18/08/2015\"," +
                "\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}," +
                "{\"species\":\"Squirtle\",\"nickname\":\"Mizu\",\"level\":2" +
                ",\"height\":30,\"weight\":30,\"dateCaught\":\"18/08/2015\"," +
                "\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}]");
        box.store(new Pokemon("Koffing", "Cloud", 20, 30, 30, "18/08/2016", "Cinnabar Island", 356));
        box.store(new Pokemon("Lapras", "Shell", 56, 30, 30, "18/08/2016", "Cinnabar Island", 356));
        Pokemon p0 = box.retrieve().get(0);
        assertEquals("Charmander", p0.getSpecies()); 
        assertEquals("Ember", p0.getNickname()); 
        assertEquals(new Integer(12), p0.getLevel()); 
        assertEquals(new Integer(30), p0.getHeight()); 
        assertEquals(new Integer(30), p0.getWeight()); 
    }

	@Test
    public void SavesBoxDataToFile() throws IOException {
        File tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        FileBox box = new FileBox(tempFile.toString());
        box.store(new Pokemon("Koffing", "Cloud", 20, 30, 30, "18/08/2016", "Cinnabar Island", 356));
        box.store(new Pokemon("Lapras", "Shell", 56, 30, 30, "18/08/2016", "Cinnabar Island", 356));
        String beforeSave = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertEquals("", beforeSave);
        box.save();
        String afterSave = inputStreamToString(new FileInputStream(tempFile.toString()));
        assertEquals("[{\"level\":20,\"species\":\"Koffing\",\"nickname\":\"Cloud\",\"height\":30,\"weight\":30," +
               "\"dateCaught\":\"18/08/2016\",\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}," +
               "{\"level\":56,\"species\":\"Lapras\",\"nickname\":\"Shell\",\"height\":30,\"weight\":30," +
               "\"dateCaught\":\"18/08/2016\",\"locationCaught\":\"Cinnabar Island\",\"currentHp\":356}]", afterSave);
    }

    private FileBox makeBoxWithStringAsFile(String fileContents) throws IOException {
        File tempFile = File.createTempFile("temp-", "-testfile");
        tempFile.deleteOnExit();
        FileWriter fw = new FileWriter(tempFile.toString());
        fw.write(fileContents);
        fw.close();
        return new FileBox(tempFile.toString());
    }

    public String inputStreamToString(InputStream inputStream) {
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n"));
        return result;
    }
}
