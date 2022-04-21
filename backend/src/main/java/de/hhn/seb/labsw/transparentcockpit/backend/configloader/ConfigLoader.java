package de.hhn.seb.labsw.transparentcockpit.backend.configloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Config Loader.
 * Load the Config from the File 'transparentcockpit.config' and Parses into Java Properties.
 */
public class ConfigLoader {

    private final Properties properties = new Properties();


    /**
     * Load the Config from the File 'transparentcockpit.config' and Parses into Java Properties.
     *
     * @throws RuntimeException Config is missing or
     *                          Config in not in the right format.
     */
    public ConfigLoader() throws RuntimeException {
        BufferedReader bufferedReader;
        try {
            bufferedReader = Files.newBufferedReader(Paths.get("transparentcockpit.config"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("'transparentcockpit.config' is missing. Please create config");
        }

        try {
            properties.load(bufferedReader);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred when reading from the input stream");
        }

    }

    public Properties getProperties() {
        return properties;
    }

}
