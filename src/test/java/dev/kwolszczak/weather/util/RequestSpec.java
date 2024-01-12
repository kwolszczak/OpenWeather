package dev.kwolszczak.weather.util;

import dev.kwolszczak.DataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

public class RequestSpec {
    private static final String propertiesPath = "test.properties";
    private static final Properties properties;
    private static final String apiKey;
    private static final String baseUri;
    private static final String locationBasePath;
    private static final String weatherBasePath;


    static {
        try {
            properties = DataProvider.getProperties(propertiesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        apiKey = properties.getProperty("apiKey");
        baseUri = properties.getProperty("baseUri");
        locationBasePath = properties.getProperty("location.basePath");
        weatherBasePath = properties.getProperty("weather.basePath");
    }


    public static RequestSpecification getRequestSpecificationForWeather() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(weatherBasePath)
                .addQueryParam("appid", apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecificationForLocation() {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setBasePath(locationBasePath)
                .addQueryParam("appid", apiKey)
                .setContentType(ContentType.JSON)
                .build();
    }
}
