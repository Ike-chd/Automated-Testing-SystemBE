package Models.Users;

import Models.Courses.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    private String phoneNumber, address;
    private Course currentCourse;

    public Student(String phoneNumber, String address, Course currentCourse, int userID, String name, String surname, String email, String idNumber, String password, AccessRole accessRole) {
        super(userID, name, surname, email, idNumber, password, accessRole);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.currentCourse = currentCourse;
    }

    public Student(String phoneNumber, String address, Course currentCourse, int userID, String name, String surname, String email, String idNumber, AccessRole accessRole) {
        super(userID, name, surname, email, idNumber, accessRole);
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.currentCourse = currentCourse;
    }

}
