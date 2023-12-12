package Services.ServicesInterfaces;

import Models.QA.Question;
import Models.Tests.Test;
import Models.Users.Student;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    public void viewReport();

    public void viewHardestTopic();

    public void viewTestMarks();

    public void takeTest(Test test);

    public void bookmarkQuestion(Question question);

    public void provideTestRating(Test test, double rating);

    public Optional<Student> getStudent(int id);

    public boolean insertStudent(Student student);

    public boolean deleteStudent(int id);

    public boolean updateStudent(Student student);

    public List<Student> getAllStudent();
}
