package org.ldr4j.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestAccountReader {

    public static Properties read() {

        InputStream inputStream = null;

        try {
            Class<?> clazz = TestAccountReader.class;

            inputStream = clazz.getResourceAsStream("/testAccount.properties");

            Properties properties = new Properties();
            properties.load(inputStream);

            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
