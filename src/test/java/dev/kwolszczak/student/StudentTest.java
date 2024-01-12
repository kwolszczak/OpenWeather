package dev.kwolszczak.student;

import dev.kwolszczak.student.model.Student;
import dev.kwolszczak.student.steps.Step;
import org.junit.jupiter.api.Test;

import static dev.kwolszczak.student.steps.Step.*;
import static org.assertj.core.api.Assertions.assertThat;

class StudentTest extends BaseTest {

    @Test
    void shouldGetStudentFromAPIandDeleteHim() {

        Student testStudent = new Student(properties.getProperty("firstName"), properties.getProperty("middleName"), properties.getProperty("lastName"), properties.getProperty("dob"));
        String newMiddleName = properties.getProperty("newMiddleName");

        //POST -create student
        int studentId = createStudent(testStudent);
        var createdStudent = getStudent(studentId);

        assertThat(createdStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(testStudent);

        //PUT-update middle name
        testStudent.setMiddle_name(newMiddleName);
        updateStudent(testStudent);
        var updatedStudent = getStudent(studentId);

        assertThat(updatedStudent.getMiddle_name()).isEqualTo(newMiddleName);

        //delete
        Step.deleteStudent(studentId);
        var deletedStudent = getStudent(studentId);

        assertThat(deletedStudent).isNull();
    }
}
