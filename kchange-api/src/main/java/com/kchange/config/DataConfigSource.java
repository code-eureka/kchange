package com.kchange.config;

import org.eclipse.microprofile.config.spi.ConfigSource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class DataConfigSource implements ConfigSource {
    Properties properties;
    public DataConfigSource() {
        properties = new Properties();
        try {
            properties.load(new ClassPathResource("./database.properties").getInputStream());
            properties.load(new ClassPathResource("./kafka.properties").getInputStream());
            System.out.println("Properties loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Map<String, String> getProperties() {
        return null;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public int getOrdinal() {
        return 0;
    }

    @Override
    public String getValue(String s) {
        return properties.getProperty(s);
    }

    @Override
    public String getName() {
        return null;
    }
}
