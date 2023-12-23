package Services;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.QA.StudentAnswerDAO;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DAOs.QuestionDB;
import DAOs.StudentAnswerDB;
import DAOs.StudentDB;
import DAOs.TestAttemptDB;
import DAOs.TestDB;
import DAOs.TopicDB;
import Models.Courses.Course;
import Models.Courses.Topic;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import Services.ServicesInterfaces.ReportService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportHandler implements ReportService {

    TestAttemptDAO tadao = new TestAttemptDB();
    TestDAO tdao = new TestDB();
    TopicDAO topdao = new TopicDB();
    QuestionDAO qdao = new QuestionDB();
    StudentAnswerDAO sadao = new StudentAnswerDB();
    StudentDAO sdao = new StudentDB();

    @Override
    public Map<Topic, Double> getHardestTopics() {
        Map<Topic, Double> hardestTopics = new HashMap<>();
        List<Topic> topics = topdao.allTopics();
        for (Topic topic : topics) {
            List<Question> questions = qdao.allQuestionUnderATopic(topic.getTopicID());
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
        Map<Topic, Double> hardestTopicsPerStudent = new HashMap<>();
        List<Topic> topics = topdao.allTopics();
        for (Topic topic : topics) {
            List<Question> questions = qdao.allQuestionUnderATopic(topic.getTopicID());
            double totalPercentage = 0;
            int questionCount = 0;
            for (Question question : questions) {
                double percentage = getPercentageOfWrongAnswersPerStudent(question, student);
                totalPercentage += percentage;
                questionCount++;
            }
            double averagePercentage = (questionCount == 0) ? 0 : totalPercentage / questionCount;
            hardestTopicsPerStudent.put(topic, averagePercentage);
        }
        return hardestTopicsPerStudent;
    }

    @Override
    public Map<Test, Double> getHardestTestsPerStudent(Student student) {
        Map<Test, Double> hardestTestsPerStudent = new HashMap<>();
        List<Test> tests = tdao.getAllTests();

        for (Test test : tests) {
            double ratingPercentage = getRatingPercentagePerStudent(test, student);
            hardestTestsPerStudent.put(test, ratingPercentage);
        }

        return hardestTestsPerStudent;
    }

    @Override
    public Map<Student, Double> getAllStudentsAndTheirAveragesInACourse(Course course) {
        Map<Student, Double> studentAverages = new HashMap<>();
        List<Student> students = sdao.getAllStudents();
        for (Student student : students) {
            double averageRating = getAverageRatingForStudentInCourse(student, course);
            studentAverages.put(student, averageRating);
        }
        return studentAverages;
    }

    @Override
    public List<TestAttempt> getAllStudentsAndTheirTestAttempt() {
        List<TestAttempt> allTestAttempts = tadao.getAllTestAttempts(); 
        Map<Student, List<TestAttempt>> studentTestAttemptsMap = new HashMap<>();
        for (TestAttempt testAttempt : allTestAttempts) {
            Student student = testAttempt.getStudent();
            studentTestAttemptsMap.computeIfAbsent(student, k -> new ArrayList<>());
            studentTestAttemptsMap.get(student).add(testAttempt);
        }
        List<TestAttempt> result = new ArrayList<>();
        for (Map.Entry<Student, List<TestAttempt>> entry : studentTestAttemptsMap.entrySet()) {
            Student student = entry.getKey();
            List<TestAttempt> testAttempts = entry.getValue();
            double averageRating = calculateAverageRating(testAttempts);
            TestAttempt averageTestAttempt = new TestAttempt();
            averageTestAttempt.setStudent(student);
            averageTestAttempt.setRating(averageRating);
            result.add(averageTestAttempt);
        }

        return result;

    }
    
    private double getPercentageOfWrongAnswersPerStudent(Question question, Student student) {
        int wrongAnswers = 0;
        int totalAttempts = 0;

        List<StudentAnswer> answers = sadao.getStudentAnswersByQuestionAndStudent(question, student);
        
        for (StudentAnswer answer : answers) {
            if (!answer.isCorrectAns()) {
                wrongAnswers++;
            }
            totalAttempts++;
        }

        return (totalAttempts == 0) ? 0 : (double) wrongAnswers / totalAttempts * 100;
    }
    
    private double getRatingPercentagePerStudent(Test test, Student student) {
        double totalRatings = 0;
        int totalAttempts = 0;

        List<TestAttempt> testAttempts = tadao.getAllTestAttemptsByTestAndStudent(test, student);

        for (TestAttempt testAttempt : testAttempts) {
            totalRatings += testAttempt.getRating();
            totalAttempts++;
        }

        return (totalAttempts == 0) ? 0 : (totalRatings / totalAttempts) * 100;
    }
    
    private double getAverageRatingForStudentInCourse(Student student, Course course) {
        return 0;//TODO
    }
    
    private double calculateAverageRating(List<TestAttempt> testAttempts) {
        double totalRating = 0;
        int totalAttempts = testAttempts.size();
        for (TestAttempt testAttempt : testAttempts) {
            totalRating += testAttempt.getRating();
        }
        return (totalAttempts == 0) ? 0 : totalRating / totalAttempts;
    }

//    @Override
//    public Map<Topic, Double> getHardestTopics() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Map<Test, Double> getHardestTests() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Map<Topic, Double> getHardestTopicsPerStudent(Student student) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Map<Test, Double> getHardestTestsPerStudent(Student student) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public Map<Student, Double> getAllStudentsAndTheirAveragesInACourse(Course course) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public List<TestAttempt> getAllStudentsAndTheirTestAttempt() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}