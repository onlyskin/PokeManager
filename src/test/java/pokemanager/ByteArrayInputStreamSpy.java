package pokemanager;

import java.io.ByteArrayInputStream;

public class ByteArrayInputStreamSpy extends ByteArrayInputStream {
    boolean closeCalled;

    public ByteArrayInputStreamSpy(byte[] buf) {
        super(buf);
        closeCalled = false;
    }

    @Override
    public void close() {
        closeCalled = true;
    }
}
