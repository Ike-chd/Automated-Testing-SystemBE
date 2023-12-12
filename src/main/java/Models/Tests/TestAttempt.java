
package Models.Tests;

import Models.QA.StudentAnswer;
import Models.Users.Student;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TestAttempt {
    private int testAttemptID;
    private Test test;
    private Student student;
    private double rating;
    private final List<StudentAnswer> answers = new ArrayList<>();

    public TestAttempt(int testAttemptID, Test test, Student student, double rating) {
        this.testAttemptID = testAttemptID;
        this.test = test;
        this.student = student;
        this.rating = rating;
    }
}
