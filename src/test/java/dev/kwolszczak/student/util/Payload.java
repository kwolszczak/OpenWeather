package dev.kwolszczak.student.util;

public class Payload {
    private static String createTemplate = """
            {
              "id":"{id}",
              "first_name": "{firstName}",
              "middle_name": "{middleName}",
              "last_name": "{lastName}",
              "date_of_birth": "{dob}}"
            }""";

    public static String createStudent(String firstName, String middleName, String lastName, String dob) {
        return updateStudent("0", firstName, middleName, lastName, dob);
    }
    public static String updateStudent(String id,String firstName, String middleName, String lastName, String dob) {
        return createTemplate
                .replaceAll("[{]id[}]", id)
                .replaceAll("[{]firstName[}]", firstName)
                .replaceAll("[{]middleName[}]", middleName)
                .replaceAll("[{]lastName[}]", lastName)
                .replaceAll("[{]dob[}]", dob);
    }
}
