package dev.kwolszczak.student.util;

import dev.kwolszczak.DataProvider;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

public class RequestSpec {

    private static final String propertiesPath = "student.properties";
    private static final Properties properties;
    private static final String baseUri;
    private static final String basePath;

    static {
        try {
            properties = DataProvider.getProperties(propertiesPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseUri = properties.getProperty("baseUri");
        basePath = properties.getProperty("basePath");
    }

    public static RequestSpecification getStudentReqSpec() {
        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseUri)
                .setBasePath(basePath)
                .build();
        return requestSpec;
    }
}
