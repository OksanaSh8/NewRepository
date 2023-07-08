package by.itclass.model.db;

import by.itclass.constants.AppConstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static Properties load(String filename) {
        Properties properties = new Properties();
        InputStream in = Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream(filename);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
