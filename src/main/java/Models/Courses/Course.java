package Models.Courses;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int courseID;
    private String courseName;
    private String courseNumber;
    private List<Module> modules = new ArrayList<>();

    public Course(int courseID, String courseName, String courseNumber) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }

    public Course(String courseName, String courseNumber) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
    }
    
}
