package pokemanager;

import java.io.*;

public class Ui {
    private final PrintStream printStream;
    private final MessageProvider messageProvider;

    public Ui(BufferedReader reader, PrintStream printStream,
            MessageProvider messageProvider) {
        this.printStream = printStream;
        this.messageProvider = messageProvider;
    }

    public void display(String output) {
        printStream.println(output);
    }

    public void startupMessage() {
       display(messageProvider.startupMessage()); 
    }
}
