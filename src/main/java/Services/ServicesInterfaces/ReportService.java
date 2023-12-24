package Services.ServicesInterfaces;

import Models.Courses.Course;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import java.util.List;
import java.util.Map;

public interface ReportService {

    public Map<String, Double> getHardestTopics();

    public Map<String, Double> getHardestTests();

    public Map<String, Double> getAllModulesAndAverageForEachPerStudent(int courseID, int studentID);

    public Map<String, Double> getHardestTopicsPerStudent(int courseID, int studentID);

    public Map<String, Double> getHardestTestsPerStudent(int courseID, int studentID);

    public Map<Student, Double> getAllStudentsAndTheirAveragesInACourse(Course course);

    public List<TestAttempt> getAllStudentsAndTheirTestAttempt();
    
    public Map<String, Double> totalMarksPerTestForStudent(int courseID, int studentID);
}
