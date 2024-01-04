package dev.kwolszczak.util;

import org.junit.jupiter.params.provider.Arguments;

import java.io.*;
import java.util.Properties;
import java.util.stream.Stream;

public class DataProvider {

    public static Properties getProperties(String path) throws IOException {

        var properties = new Properties();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream resourceAsStream = classLoader.getResourceAsStream(path)) {
            if (resourceAsStream == null) {
                throw new FileNotFoundException(" File not found: " + path);
            }

            try (var reader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
                properties.load(reader);
                return properties;
            }
        }
    }

    public static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("London"),
                Arguments.of("Oxford"),
                Arguments.of("Gdańsk"));
    }
}
