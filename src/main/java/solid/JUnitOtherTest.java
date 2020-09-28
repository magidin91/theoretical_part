package solid;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.openjdk.jmh.annotations.Timeout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JUnitOtherTest {
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

 /*   @Rule
    public final Timeout timeout = new Timeout(1000);*/

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Ignore
    @Test
    public void anotherInfinity() {
        while (true) ;
    }

    @Test
    public void testFileWriting() throws IOException {
        final File log = folder.newFile("debug.log");
        final FileWriter logWriter = new FileWriter(log);
        logWriter.append("Hello, ");
        logWriter.append("World!!!");
        logWriter.flush();
        logWriter.close();
    }

    @Test
    public void testExpectedException() throws IOException {
        thrown.expect(NullPointerException.class);
       // StringUtils.toHexString(null);
    }
}