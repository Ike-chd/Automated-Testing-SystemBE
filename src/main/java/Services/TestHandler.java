package Services;

import DAOs.AnswerDB;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.DAOControllers.QA.AnswerDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.ModuleDB;
import DAOs.QuestionDB;
import DAOs.TestDB;
import DAOs.TopicDB;
import Models.Courses.Topic;
import Models.Courses.Module;
import Models.QA.Question;
import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

public class TestHandler implements TestService {

    private TestDAO tdao = new TestDB();
    private QuestionDAO qdao = new QuestionDB();
    private ModuleDAO mdao = new ModuleDB();
    private TopicDAO topdao = new TopicDB();
    private AnswerDAO adao = new AnswerDB();

    @Override
    public Optional<Test> getTest(int TestID) {
        Test test = tdao.getTest(TestID);
        if (test != null) {
            test.setTopics(topdao.getAllTopicsInATest(TestID));
            for (Topic topic : test.getTopics()) {
                test.getQuestions().addAll(qdao.allQuestionUnderATopic(topic.getTopicID()));
            }
        }
        return Optional.ofNullable(test);
    }

    @Override
    public boolean insertTest(Test test) {
        if (tdao.insetTest(test)) {
            int id = tdao.getLastInsertID();
            for (Topic topic : test.getTopics()) {
                topdao.insertTopicIntoTest(id, topic.getTopicID());
            }
            return true;
        }
        return false;
    }

    @Override
    public double getTotalWeight(int moduleId) {
        double total = 0;
        for (Test test : tdao.getAllTestsByModuleID(moduleId)) {
            total += test.getWeight();
        }
        return total;
    }

    @Override
    public boolean deleteTest(int testId) {
        return tdao.deleteTest(testId);
    }

    @Override
    public boolean updateTest(Test test) {
        return tdao.updateTest(test);
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = tdao.getAllTests();
        for (Test test : tests) {
            test.setQuestions(tdao.getAllQuestionsInTest(test.getTestID()));
        }
        return tests;
    }

    @Override
    public List<Test> getAllTestsByModule(int id) {
        List<Test> tests = tdao.getAllTestsByModuleID(id);
        for (Test test : tests) {
            test.setQuestions(tdao.getAllQuestionsInTest(test.getTestID()));
        }
        return tests;
    }

    @Override
    public int getTotalTestMarks(int testId) {
        List<Topic> topics = topdao.getAllTopicsInATest(testId);
        List<Question> questions = new ArrayList<>();
        for (Topic topic : topics) {
            questions.addAll(qdao.allQuestionUnderATopic(topic.getTopicID()));
        }
        int total = 0;
        for (Question question : questions) {
            total += question.getMarkAllocation();
        }
        return total;
    }

    @Override
    public List<Test> allTestsInACourse(int courseId) {
        List<Test> allTestsInACourse = new ArrayList<>();
        for (Module module : mdao.getAllModulesInACourse(courseId)) {
            allTestsInACourse.addAll(tdao.getAllTestsByModuleID(module.getModuleID()));
        }
        for (Test test : allTestsInACourse) {
            for (Topic topic : topdao.getAllTopicsInATest(test.getTestID())) {
                test.getQuestions().addAll(qdao.allQuestionUnderATopic(topic.getTopicID()));
            }
            for (Question question : test.getQuestions()) {
                question.getAnswers().addAll(adao.allQuestionAnswers(question.getQuestionID()));
            }
        }
        return allTestsInACourse;
    }

    public List<Test> allTestsInAModule(int courseId) {
        List<Test> allTestsInACourse = new ArrayList<>();
        for (Module module : mdao.getAllModulesInACourse(courseId)) {
            allTestsInACourse.addAll(tdao.getAllTestsByModuleID(module.getModuleID()));
        }
        return allTestsInACourse;
    }
}
