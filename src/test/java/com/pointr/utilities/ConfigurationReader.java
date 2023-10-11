package com.pointr.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties = new Properties();

    static {

        try {
            String filePath = System.getProperty("user.dir") + File.separator + "configuration.properties";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return properties.getProperty("base_url");
    }

    public static String getUsername() {
        String username = System.getProperty("username");

        if (username != null) {
            return username;
        }

        return properties.getProperty("username");
    }

    public static String getPassword() {
        String password = System.getProperty("password");

        if (password != null) {
            return password;
        }

        return properties.getProperty("password");
    }

    public static String getSearchTerm() {
        String searchTerm = System.getProperty("search_term");

        if (searchTerm != null) {
            return searchTerm;
        }

        return properties.getProperty("search_term");
    }
}

