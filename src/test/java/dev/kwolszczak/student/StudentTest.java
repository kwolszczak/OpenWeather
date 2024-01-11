package dev.kwolszczak.student;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class StudentTest {

    @Test
    void shouldGetStudentFromAPIandDeleteHim() {

        String bodyPayload = """
                {
                  "first_name": "sii Test student",
                  "middle_name": "sample string 3",
                  "last_name": "siiCorp",
                  "date_of_birth": "12/08/2020"
                }""";

        String baseUri = "https://thetestingworldapi.com";
        String basePath = "/api/studentsDetails";

        //create student
        int studentId = given().log().all()
                .baseUri(baseUri)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .body(bodyPayload)

                .when().post()
                .then().log().all()
                .assertThat().statusCode(201)
                .extract().jsonPath().getInt("id");
//get student's details
        given().baseUri(baseUri)
                .basePath(basePath)
                .pathParam("id", studentId)
                .when().get("/{id}")
                .then().log().all().assertThat().statusCode(200);

        //update middle name
        String newMiddleName = "tom tom";
        String bodyUpdateMiddleName = """
                {
                  "id": {id},
                  "first_name": "sii Test student",
                  "middle_name": "{name}",
                  "last_name": "siiCorp",
                  "date_of_birth": "12/08/2020"
                }"""
                .replaceAll("[{]name[}]", newMiddleName)
                .replaceAll("[{]id[}]", String.valueOf(studentId));

        given().log().all().
                baseUri(baseUri)
                .basePath(basePath)
                .contentType(ContentType.JSON)
                .body(bodyUpdateMiddleName)
                .pathParam("id", studentId)
                .when().put("/{id}")
                .then().log().all()
                .assertThat().statusCode(200)
                .body("status",equalTo("true"))
                .body("msg", Matchers.containsString("success"));

        //get student's details
        given().baseUri(baseUri)
                .basePath(basePath)
                .pathParam("id", studentId)
                .when().get("/{id}")
                .then().log().all().assertThat().statusCode(200);
//delete
        given().baseUri(baseUri)
                .basePath(basePath)
                .pathParam("id",studentId)
                .when().delete("/{id}")
                .then().log().all()
                .assertThat().statusCode(200);

        //verify student
        given().baseUri(baseUri)
                .basePath(basePath)
                .pathParam("id", studentId)
                .when().get("/{id}")
                .then().log().all().assertThat().statusCode(200)
                .body("status", equalTo("false"))
                .body("msg",equalTo("No data Found"));


    }
}
