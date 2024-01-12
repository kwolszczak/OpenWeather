package dev.kwolszczak.student.util;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec {

    public static ResponseSpecification getStudentResSpec() {
        ResponseSpecification responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
        return responseSpec;
    }
}
