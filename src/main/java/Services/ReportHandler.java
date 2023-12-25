package Services;

import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.QA.StudentAnswerDAO;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DAOs.ModuleDB;
import DAOs.QuestionDB;
import DAOs.StudentAnswerDB;
import DAOs.StudentDB;
import DAOs.TestAttemptDB;
import DAOs.TestDB;
import DAOs.TopicDB;
import Models.Courses.Course;
import Models.Courses.Module;
import Models.Courses.Topic;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import Services.ServicesInterfaces.ReportService;
import Services.ServicesInterfaces.TestService;
import Services.ServicesInterfaces.TopicService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReportHandler implements ReportService {

    TestAttemptDAO tadao = new TestAttemptDB();
    TestDAO tdao = new TestDB();
    TestService ts = new TestHandler();
    TopicDAO topdao = new TopicDB();
    QuestionDAO qdao = new QuestionDB();
    StudentAnswerDAO sadao = new StudentAnswerDB();
    StudentDAO sdao = new StudentDB();
    ModuleDAO mdao = new ModuleDB();
    TopicService tops = new TopicHandler();

    @Override
    public Map<String, Double> getAllModulesAndAverageForEachPerStudent(int courseID, int studentID) {
        Map<String, Double> avgs = new HashMap<>();
        List<Module> modules = mdao.getAllModulesInACourse(courseID);
        for (Module module : modules) {
            double total = 0;
            double tatotal = 0;
            List<Test> tests = tdao.getAllTestsByModuleID(module.getModuleID());
            for (Test test : tests) {
                TestAttempt attempt = tadao.getAllTestAttemptsByTestAndStudent(test.getTestID(), studentID);
                if (attempt != null) {
                    total += ts.getTotalTestMarks(test.getTestID());
                    tatotal += attempt.getTotalMarks();
                }
            }
            if (total > 0) {
                avgs.put(module.getModuleName(), (tatotal / total) * 100);
            } else {
                avgs.put(module.getModuleName(), 0.0);
            }
        }
        return avgs;
    }

    public Map<String, Double> getAllModulesAndAverage() {
        Map<String, Double> avgs = new HashMap<>();
        List<Module> modules = mdao.allModules();
        for (Module module : modules) {
            double total = 0;
            double tatotal = 0;
            List<Test> tests = tdao.getAllTestsByModuleID(module.getModuleID());
            for (Test test : tests) {
                List<TestAttempt> attempts = tadao.getAllTestAttemptsByTest(test);
                if (!attempts.isEmpty()) {
                    total += ts.getTotalTestMarks(test.getTestID()) * attempts.size();
                    for (TestAttempt attempt : attempts) {
                        tatotal += attempt.getTotalMarks();
                    }
                }
            }
            if (total > 0) {
                avgs.put(module.getModuleName(), (tatotal / total) * 100);
            } else {
                avgs.put(module.getModuleName(), 0.0);
            }
        }
        return avgs;
    }

    @Override
    public Map<String, Double> getHardestTopics() {
        Map<String, Double> hardestTopics = new HashMap<>();
        List<Topic> topics = topdao.allTopics();
        for (Topic topic : topics) {
            List<Question> questions = qdao.allQuestionUnderATopic(topic.getTopicID());
            double allQuestions = 0.0;
            for (Question question : questions) {
                allQuestions += getPercentageOfWrongAnswers(question);
            }
            hardestTopics.put(topic.getTopicName(), allQuestions);
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
    public Map<String, Double> getHardestTests() {
        Map<String, Double> hardestTests = new HashMap<>();
        List<Test> tests = tdao.getAllTests();
        for (Test test : tests) {
            hardestTests.put(test.getTestName(), getRatingPercentage(test));
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
        return (numOfRatings==0) ? 0 : (ratings / numOfRatings) * 100;
    }

    @Override
    public Map<String, Double> getHardestTopicsPerStudent(int courseID, int studentID) {
        Map<String, Double> hardestTopicsPerStudent = new HashMap<>();
        Set<Integer> topicIDs = tops.getAllTopicsInACourse(courseID);
        for (Integer topicID : topicIDs) {
            List<Question> questions = qdao.allQuestionUnderATopic(topicID);
            double totalPercentage = 0;
            int questionCount = 0;
            for (Question question : questions) {
                double percentage = getPercentageOfWrongAnswersPerStudent(question.getQuestionID(), studentID);
                totalPercentage += percentage;
                questionCount++;
            }
            double averagePercentage = (questionCount == 0) ? 0 : totalPercentage / questionCount;
            hardestTopicsPerStudent.put(topdao.getTopic(topicID).getTopicName(), averagePercentage);
        }
        return hardestTopicsPerStudent;
    }

    @Override
    public Map<String, Double> getHardestTestsPerStudent(int courseID, int studentID) {
        Map<String, Double> hardestTestsPerStudent = new HashMap<>();
        List<Test> tests = ts.allTestsInACourse(courseID);
        for (Test test : tests) {
            TestAttempt attempt = tadao.getAllTestAttemptsByTestAndStudent(test.getTestID(), studentID);
            if(attempt != null){
            hardestTestsPerStudent.put(test.getTestName(), attempt.getRating());
            } else {
                hardestTestsPerStudent.put(test.getTestName(), 0.0);
            }
        }
        return hardestTestsPerStudent;
    }

    @Override
    public Map<String, Double> totalMarksPerTestForStudent(int courseID, int studentID) {
        Map<String, Double> hardestTestsPerStudent = new HashMap<>();
        List<Test> tests = ts.allTestsInACourse(courseID);
        for (Test test : tests) {
            TestAttempt attempt = tadao.getAllTestAttemptsByTestAndStudent(test.getTestID(), studentID);
            hardestTestsPerStudent.put(test.getTestName(), attempt.getTotalMarks() * 1.0);
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

    private double getPercentageOfWrongAnswersPerStudent(int questionID, int studentID) {
        double wrongAnswers = 0.0;
        double totalAttempts = 0.0;
        List<StudentAnswer> answers = sadao.getStudentAnswersByQuestionAndStudent(questionID, studentID);
        for (int i = 0; i < answers.size(); i++) {
            if (!answers.get(i).isCorrectAns()) {
                wrongAnswers++;
            }
            totalAttempts++;
        }
        return (totalAttempts == 0) ? 0 : (double) ((wrongAnswers / totalAttempts) * 100);
    }

//    private double getRatingPercentagePerStudent(Test test, Student student) {
//        double totalRatings = 0;
//        int totalAttempts = 0;
//
//        List<TestAttempt> testAttempts = tadao.getAllTestAttemptsByTestAndStudent(test, student);
//
//        for (TestAttempt testAttempt : testAttempts) {
//            totalRatings += testAttempt.getRating();
//            totalAttempts++;
//        }
//
//        return (totalAttempts == 0) ? 0 : (totalRatings / totalAttempts) * 100;
//    }
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

    @Override
    public double getCourseAVG(int courseID, int studentID) {
        Map<String, Double> allMAVG = getAllModulesAndAverageForEachPerStudent(courseID, studentID);
        Set<String> mnames = allMAVG.keySet();
        double avg = 0.0;
        double totamlM = 0.0;
        for (String mname : mnames) {
            if(allMAVG.get(mname) > 0){
                avg += allMAVG.get(mname);
                totamlM++;
            }
        }
        return avg/totamlM;
    }
}
