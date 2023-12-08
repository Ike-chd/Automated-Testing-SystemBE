package Services;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.QA.StudentAnswerDAO;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import Models.Courses.Course;
import Models.Courses.Topic;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import Services.ServicesInterfaces.ReportService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportHandler implements ReportService {

    TestAttemptDAO tadao;
    TestDAO tdao;
    TopicDAO topdao;
    QuestionDAO qdao;
    StudentAnswerDAO sadao;

    @Override
    public Map<Topic, Double> getHardestTopics() {
        Map<Topic, Double> hardestTopics = new HashMap<>();
        List<Topic> topics = topdao.allTopics();
        for (Topic topic : topics) {
            List<Question> questions = qdao.allQuestionUnderATopic(topic);
            for (Question question : questions) {
                hardestTopics.put(topic, getPercentageOfWrongAnswers(question));
            }
        }
        return hardestTopics;
    }

    public double getPercentageOfWrongAnswers(Question question) {
        int percentageOfWrongAnswers = 0;
        int numOfAnswers = 0;
        List<StudentAnswer> answers = sadao.getStudentAnswersByQuestion(question);
        for (StudentAnswer answer : answers) {
            percentageOfWrongAnswers += (answer.isCorrectAns()) ? 0 : 1;
            numOfAnswers++;
        }
        return (percentageOfWrongAnswers / numOfAnswers) * 100;
    }

    @Override
    public Map<Test, Double> getHardestTests() {
        Map<Test, Double> hardestTests = new HashMap<>();
        List<Test> tests = tdao.getAllTests();
        for (Test test : tests) {
            hardestTests.put(test, getRatingPercentage(test));
        }
        return hardestTests;
    }

    public double getRatingPercentage(Test test) {
        double ratings = 0;
        int numOfRatings = 0;
        List<TestAttempt> testAttempts = tadao.getAllTestAttemptsByTest(test);
        for (TestAttempt testAttempt : testAttempts) {
            ratings += testAttempt.getRating();
            numOfRatings++;
        }
        return (ratings/numOfRatings)*100;
    }

    @Override
    public Map<Topic, Double> getHardestTopicsPerStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Test, Double> getHardestTestsPerStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Map<Student, Double> getAllStudentsAndTheirAveragesInACourse(Course course) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TestAttempt> getAllStudentsAndTheirTestAttempt() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}