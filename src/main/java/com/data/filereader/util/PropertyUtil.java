package com.data.filereader.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final Properties property = loadConfiguration("application.properties");

    private static Properties loadConfiguration(String fileName){

        Properties prop = new Properties();
        try{
            final InputStream inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            prop.load(inStream);
        }
        catch(IOException io) {
            io.printStackTrace();
        }
        return prop;
    }

    public static String getPropertyKey(String keyName) {
        return property.getProperty(keyName);
    }

}
