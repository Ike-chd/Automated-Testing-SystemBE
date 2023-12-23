package Services;

import DAOs.AnswerDB;
import DAOs.DAOControllers.QA.AnswerDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.QuestionDB;
import DAOs.TestAttemptDB;
import Models.QA.Answer;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Services.ServicesInterfaces.TestAttemptService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestAttemptHandler implements TestAttemptService {
    private TestAttemptDAO tadao = new TestAttemptDB();
    private AnswerDAO adao = new AnswerDB();
    private QuestionDAO qdao = new QuestionDB();

    @Override
    public Optional<TestAttempt> getTestAttempt(int attemptID) {
        return Optional.ofNullable(tadao.getTestAttempt(attemptID));
    }

    @Override
    public boolean addTestAttempt(TestAttempt testAttempt) {
        return tadao.insertTestAttempt(testAttempt);
    }
    
    public void evaluateAnswers(List<StudentAnswer> answers, List<Question> questions){
        for (Question question : questions) {
            List<Answer> ca = adao.getAllCorrectAnswers(question.getQuestionID());
        }
    }
    
    public List<StudentAnswer> getAllStudentAnswersByQuestion(){
        return null;
    }

    @Override
    public boolean updateTestAttempt(TestAttempt testAttempt) {
        
        return false;
    }

    @Override
    public boolean deleteTestAttempt(int attemptID) {
        
        return false;
    }
}
