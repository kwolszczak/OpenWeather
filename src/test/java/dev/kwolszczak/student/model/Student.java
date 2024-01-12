package dev.kwolszczak.student.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student {
    private int id;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String date_of_birth;

    public Student(String firstName, String middleName, String lastName, String dob) {
        this.first_name = firstName;
        this.middle_name = middleName;
        this.last_name = lastName;
        this.date_of_birth = dob;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                '}';
    }
}
