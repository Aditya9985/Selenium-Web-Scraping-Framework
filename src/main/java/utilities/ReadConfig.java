package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

    public static String getValue(String keyname) throws Exception {
        String value = "";

        FileInputStream file = new FileInputStream("config.properties");

        //Load the file
        Properties prop = new Properties();
        prop.load(file);
        value = prop.getProperty(keyname);

        return value;
    }
}
