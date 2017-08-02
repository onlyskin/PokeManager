import java.io.PrintStream;
import java.io.InputStream;

public class App {
    private InputStream in;
    private PrintStream out;

    public App(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }

    public void acceptInput() {
        this.out.println("Gyarados");
    }
}
