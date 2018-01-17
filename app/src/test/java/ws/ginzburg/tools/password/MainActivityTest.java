package ws.ginzburg.tools.password;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmitry Ginzburg &lt;dmitry@novisign.com%gt;
 * @since Jan 17 2018
 */
public class MainActivityTest {
    private MainActivity mainActivity;

    @Before
    public void initClass() throws Exception {
        mainActivity = new MainActivity();
    }

    @Test
    public void generatePassword() throws Exception {
        assertEquals(
                "abacabadb2f8057da",
                mainActivity.generatePassword("abacaba", "dabacaba", "google.com", 10));
    }

    @Test
    public void byteArrayToHex() throws Exception {
        byte[] byteArray = "Hello, World!".getBytes();
        assertEquals("48656c6c6f2c20576f726c6421", mainActivity.byteArrayToHex(byteArray));
    }

    @Test
    public void simplifyServiceName() throws Exception {
        assertEquals("googlecomtest", mainActivity.simplifyServiceName("https://פתאום תווים לא לטיניים מופיעים google.com/test"));
    }

}