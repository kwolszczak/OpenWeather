package dev.kwolszczak.student;

import dev.kwolszczak.DataProvider;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    private static final String propertiesPath = "student.properties";
    protected static Properties properties;

    @BeforeEach
    void setUp() {
        try {
            properties = DataProvider.getProperties(propertiesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
