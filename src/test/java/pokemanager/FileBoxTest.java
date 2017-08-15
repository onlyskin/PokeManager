package pokemanager;

import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

import static org.junit.Assert.*;
import java.util.stream.Collectors;

public class FileBoxTest {
    private FileBox box;

	@Test
    public void RetrievesBoxContentsAsString() throws Exception {
        makeBoxWithStringAsFile("[{\"species\":\"Charmander\",\"nickname\":\"Ember\",\"level\":12}," +
                                "{\"species\":\"Squirtle\",\"nickname\":\"Mizu\",\"level\":2}]");
        box.store("Koffing", "Cloud", 20);
        box.store("Lapras", "Shell", 56);
        assertEquals("Ember - lv.12 Charmander\n" +
                     "Mizu - lv.2 Squirtle\n" +
                     "Cloud - lv.20 Koffing\n" +
                     "Shell - lv.56 Lapras\n", box.retrieve());
    }

	@Test
    public void SavesBoxDataToFile() throws IOException {
       File tempFile = File.createTempFile("temp-", "-testfile");
       tempFile.deleteOnExit();
       FileBox box = new FileBox(tempFile.toString());
       box.store("Koffing", "Cloud", 20);
       box.store("Lapras", "Shell", 56);
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
