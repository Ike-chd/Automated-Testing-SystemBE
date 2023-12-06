package Models.Users;

import Models.Courses.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student extends User {

    private Course currentCourse;

    public Student(int userID, String name, String surname, String email, String idNumber, String address, String password, Course currentCourse) {
        super(userID, name, surname, email, idNumber, address, password);
        this.currentCourse = currentCourse;
    }
}
