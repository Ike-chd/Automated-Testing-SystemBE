package Models.Tests;

import Models.StudentAnswer.StudentAnswer;
import Models.Users.Student;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class TestAttempt {

    private int attemptID;
    private Student student;
    private Test test;
    private double rating;
    private Date startDateTime;
    private Date endDateTime;
    private final List<StudentAnswer> answers = new ArrayList<>();
}
