package dev.kwolszczak.student;

import dev.kwolszczak.student.model.Student;
import dev.kwolszczak.student.steps.Step;
import dev.kwolszczak.student.BaseTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest extends BaseTest {

    @Test
    void shouldGetStudentFromAPIandDeleteHim() {

        Student testStudent = new Student(properties.getProperty("firstName"), properties.getProperty("middleName"), properties.getProperty("lastName"), properties.getProperty("dob"));

        //POST -create student
        int studentId = Step.createStudent(testStudent);
        var createdStudent = Step.getStudent(studentId);

        assertThat(createdStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(testStudent);

        //PUT-update middle name
        String newMiddleName = properties.getProperty("newMiddleName");
        testStudent.setId(studentId);
        testStudent.setMiddle_name(newMiddleName);
        Step.updateStudent(testStudent);

        var updatedStudent = Step.getStudent(studentId);
        assertThat(updatedStudent.getMiddle_name()).isEqualTo(newMiddleName);

        //delete
        Step.deleteStudent(studentId);

        var deletedStudent = Step.getStudent(studentId);
        assertThat(deletedStudent).isNull();
    }
}
