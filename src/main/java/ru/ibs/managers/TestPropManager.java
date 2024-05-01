package ru.ibs.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {

    private final Properties properties = new Properties ();
    private static TestPropManager INSTANCE = null;

    private TestPropManager() {
        loadApplicationProperties();
    }

    public static TestPropManager getPropertyManager() {
        if (INSTANCE == null) {
            INSTANCE = new TestPropManager();
        }
        return INSTANCE;
    }

    private void loadApplicationProperties() {
        try {
            properties.load(new FileInputStream("src/main/resources/environment.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
