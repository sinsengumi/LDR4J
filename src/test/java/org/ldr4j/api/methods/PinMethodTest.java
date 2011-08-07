package org.ldr4j.api.methods;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ldr4j.LDRException;
import org.ldr4j.api.LDRClient;
import org.ldr4j.api.TestAccountReader;
import org.ldr4j.api.response.Pin;

public class PinMethodTest {

    private static LDRClient ldrClient;

    private static Properties properties;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        properties = TestAccountReader.read();
        ldrClient = new LDRClient(
                properties.getProperty("livedoorId"), properties.getProperty("password"));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        ldrClient.shutdown();
    }

    @Before
    public void setUp() throws Exception {
        ldrClient.clearPin();
    }

    @After
    public void tearDown() throws Exception {
        ldrClient.clearPin();
    }

    @Test
    public void testGetAllPins() throws LDRException {
        Pin[] beforePins = ldrClient.getAllPins();

        ldrClient.addPin("http://www.livedoor.com/", "livedoor");

        Pin[] afterPins = ldrClient.getAllPins();

        for (Pin pin : afterPins) {
            // 設定したピンが正しく登録されているか
            if (pin.getLink().equals("http://www.livedoor.com/")) {
                assertTrue(true);
            }

            // 設定したピンが正しく登録されているか
            if (pin.getTitle().equals("livedoor")) {
                assertTrue(true);
            }
        }

        // ピン数が1増えていること
        assertEquals(beforePins.length + 1, afterPins.length);
    }

    @Test
    public void testAddPin() throws LDRException {
        ldrClient.addPin("http://www.livedoor.com/", "livedoor");
    }

    @Test(expected = LDRException.class)
    public void testAddPinBlankTitle() throws LDRException {
        ldrClient.addPin("http://www.livedoor.com/", "");
    }

    @Test(expected = LDRException.class)
    public void testAddPinBlankLink() throws LDRException {
        ldrClient.addPin("", "livedoor");
    }

    @Test
    public void testRemovePin() throws LDRException {
        Pin[] pins = ldrClient.getAllPins();

        ldrClient.addPin("http://www.livedoor.com/", "livedoor");
        ldrClient.removePin("http://www.livedoor.com/");

        Pin[] pins2 = ldrClient.getAllPins();

        assertEquals(pins.length, pins2.length);
    }

    @Test(expected = LDRException.class)
    public void testRemovePinBlankURL() throws LDRException {
        ldrClient.removePin("");
    }

    @Test(expected = LDRException.class)
    public void testRemovePinMissTypeURL() throws LDRException {

        try {
            ldrClient.addPin("http://www.livedoor.com/", "livedoor");
            ldrClient.removePin("http://www.livedoor.net/");
        } finally {
            ldrClient.removePin("http://www.livedoor.com/");
        }
    }

    @Test
    public void testClearPin() {
        try {
            ldrClient.addPin("http://www.livedoor.com/", "livedoor");

            ldrClient.clearPin();

            Pin[] pins = ldrClient.getAllPins();

            assertEquals(0, pins.length);

        } catch (LDRException e) {
            e.printStackTrace();
        }
    }

}
