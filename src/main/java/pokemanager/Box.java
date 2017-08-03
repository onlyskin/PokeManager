package pokemanager;

import com.oracle.tools.packager.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Box {
    private final InputStream in;

    public Box(InputStream in) {
        this.in = in;
    }

    public String retrieve() {
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);
        return result;
    }
    public void store(String pokemon) {}
}
