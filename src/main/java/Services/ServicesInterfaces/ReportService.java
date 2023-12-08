package Services.ServicesInterfaces;

import Models.Courses.Course;
import Models.Courses.Topic;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import java.util.List;
import java.util.Map;

public interface ReportService {
    public Map<Topic, Double> getHardestTopics();
    public Map<Test, Double> getHardestTests();
    public Map<Topic, Double> getHardestTopicsPerStudent(Student student);
    public Map<Test, Double> getHardestTestsPerStudent(Student student);
    public Map<Student, Double> getAllStudentsAndTheirAveragesInACourse(Course course);
    public List<TestAttempt> getAllStudentsAndTheirTestAttempt();
}