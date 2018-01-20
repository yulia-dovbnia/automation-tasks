package twitter.properties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropReader {
    private final static String ENV_PROP = "keys/env.property";

    private static String readProperty(String fileName, String propertyName) {
        Properties p = new Properties();
        try {
            p.load(new FileReader(new File(System.getProperty("user.dir") +"/src/main/resources/"+ fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p.getProperty(propertyName);
    }

    public static String getKeyProperty(Keys key) {
        return readProperty(getLocationProperty(ENV_PROP,"keys" ), key.getValue());
    }

    private static String getLocationProperty(String path, String value) {
        return readProperty(path, value);
    }

}


