package com.trading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;


public class Main {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException, NoSuchAlgorithmException {
        try {
            loadProperties("secrets.properties");
        } catch (IOException ioe) {
            LOGGER.error("Failed to load secrets.", ioe);
        }
    }

    public static void loadProperties(String src) throws IOException {
        Properties props = new Properties();
        props.load(ClassLoader.getSystemResource(src).openStream());
        props.forEach((k, v) -> System.setProperty(String.valueOf(k), String.valueOf(v)));
    }

}