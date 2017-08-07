package pokemanager;

import java.io.*;

public class App {
    private BufferedReader reader;
    private PrintStream out;
    private Box box;
    private String storageFilename;

    public App(InputStream in, PrintStream out, Box box, String storageFilename) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        this.box = box;
        this.storageFilename = storageFilename;
    }

    public void acceptInput() {
        try {
            String line = reader.readLine();
            handleInputLine(line);
        } catch (Exception IOException) {}
    }

    private void outputStartMessage() {
        out.println("Commands:\n'box' to see stored Pokemon" +
                "\n'store SPECIES NICKNAME' to store a Pokemon" +
                "\n'save' to save your stored Pokemon for next time\n");
    }

    private void run() {
        outputStartMessage();
        while (true) {
            acceptInput();
        }
    }

    private void handleInputLine(String line) throws IOException {
        if (line.startsWith("store ")) {
            box.store(line.substring(6));
            out.println("Stored!\n");
        } else if (line.equals("save")) {
            save();
        } else if (line.equals("box")) {
            out.println(getBoxContents());
        }
    }

    private String getBoxContents() {
        return box.retrieve();
    }

    private void save() throws IOException {
        String contents = box.getDataString();
        FileWriter fw = new FileWriter(storageFilename);
        fw.write(contents);
        fw.close();
        out.println("Saved!\n");
    }

    public static void main(String[] args) throws IOException {
        String storageFilename = "/Users/sam/Documents/pokemanager/data/data";
        FileInputStream inputData = new FileInputStream(storageFilename);
        App app = new App(System.in,
                          System.out,
                          new Box(inputData),
                          storageFilename);
        app.run();
    }
}
