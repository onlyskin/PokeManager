package pokemanager;

import java.io.*;

public class App {
    private BufferedReader reader;
    private PrintStream out;
    private Box box;
    private String storageFilename;
    private RetrieveCommand rc;
    private StoreCommand sc;

    public App(InputStream in,
               PrintStream out,
               Box box,
               String storageFilename,
               RetrieveCommand rc,
               StoreCommand sc) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        this.box = box;
        this.storageFilename = storageFilename;
        this.rc = rc;
        this.sc = sc;
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
            sc.execute(line);
        } else if (line.equals("save")) {
            save();
            out.println("Saved!\n");
        } else if (line.equals("box")) {
            rc.execute();
        } else {
            out.println("Please enter a valid command.\n");
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
    }

    public static void main(String[] args) throws IOException {
        String storageFilename = "/Users/sam/Documents/pokemanager/data/data";
        FileInputStream inputData = new FileInputStream(storageFilename);
        Box box = new Box(inputData);
        PrintStream out = System.out;
        App app = new App(System.in,
                          out,
                          box,
                          storageFilename,
                          new RetrieveCommand(box, out),
                          new StoreCommand(box, out));
        app.run();
    }
}
