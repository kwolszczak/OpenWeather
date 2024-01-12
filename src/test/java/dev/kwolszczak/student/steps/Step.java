package dev.kwolszczak.student.steps;

import dev.kwolszczak.student.model.Student;
import dev.kwolszczak.student.util.RequestSpec;
import dev.kwolszczak.student.util.ResponseSpec;

import static io.restassured.RestAssured.given;

public class Step {

    public static int createStudent(Student student) {
        return given().spec(RequestSpec.getStudentReqSpec())
                      .body(student)
                .when().post()
                .then().assertThat().statusCode(201)
                .extract().as(Student.class).getId();
    }

    public static Student getStudent(int studentId) {
        return given().spec(RequestSpec.getStudentReqSpec())
                        .pathParam("id", studentId)
                .when().get("/{id}")
                .then().spec(ResponseSpec.getStudentResSpec())
                .extract().jsonPath().getObject("data",Student.class);
    }

    public static void updateStudent(Student student) {
        given().spec(RequestSpec.getStudentReqSpec())
                .body(student)
                .pathParam("id", student.getId())
        .when().put("/{id}")
        .then().spec(ResponseSpec.getStudentResSpec());
    }

    public static void deleteStudent(int studentId) {
        given().spec(RequestSpec.getStudentReqSpec())
                .pathParam("id", studentId)
        .when().delete("/{id}")
        .then().spec(ResponseSpec.getStudentResSpec());
    }
}
