package pokemanager;

import java.io.*;

public class App {
    private BufferedReader reader;
    private PrintStream out;
    private Box box;

    public App(InputStream in, PrintStream out, Box box) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        this.box = box;
    }

    public void acceptInput() {
        try {
            String line = reader.readLine();
            handleInputLine(line);
        } catch (Exception IOException) {}
    }

    private void handleInputLine(String line) {
        if (line.startsWith("store ")) {
            box.store(line.substring(6));
        } else out.println(getBoxContents());
    }

    private String getBoxContents() {
        return box.retrieve();
    }

    public static void main(String[] args) {
        App app = new App(System.in, System.out, new Box());
        while (true) {
                app.acceptInput();
        }
    }
}
