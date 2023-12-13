package Services;

import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.QuestionDB;
import DAOs.TestDB;
import Models.QA.Question;
import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;

import java.util.Optional;

public class TestHandler implements TestService {

    private TestDAO tdao = new TestDB();
    private QuestionDAO qdao = new QuestionDB();

    @Override
    public Optional<Test> getTest(int TestID) {
        Test test = tdao.getTest(TestID);
        if(test != null){
            test.getQuestions().addAll(qdao.allQuestionsUnderATest(TestID));
        }
        return Optional.ofNullable(test);
    }

    @Override
    public void insertTest(Test test) {
        
    }

    @Override
    public void addQuestion(Test test, Question Question) {
    }
}
