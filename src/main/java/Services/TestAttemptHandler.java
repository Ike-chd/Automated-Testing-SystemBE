package Services;

import DAOs.AnswerDB;
import DAOs.DAOControllers.QA.AnswerDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.QA.StudentAnswerDAO;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.QuestionDB;
import DAOs.StudentAnswerDB;
import DAOs.TestAttemptDB;
import Models.QA.Answer;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import Services.ServicesInterfaces.TestAttemptService;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestAttemptHandler implements TestAttemptService {

    private TestAttemptDAO tadao = new TestAttemptDB();
    private AnswerDAO adao = new AnswerDB();
    private QuestionDAO qdao = new QuestionDB();
    private StudentAnswerDAO sadao = new StudentAnswerDB();

    @Override
    public Optional<TestAttempt> getTestAttempt(int attemptID) {
        return Optional.ofNullable(tadao.getTestAttempt(attemptID));
    }

    @Override
    public boolean addTestAttempt(TestAttempt testAttempt) {
        testAttempt.setAnswers(evaluateAnswers(testAttempt.getAnswers(), testAttempt.getTest().getQuestions(), testAttempt.getStudent(), testAttempt.getTest()));
        setTotalMarks(testAttempt);
        for (StudentAnswer answer : testAttempt.getAnswers()) {
            sadao.insertStudentAnswer(answer);
        }
        return tadao.insertTestAttempt(testAttempt);
    }

    public List<StudentAnswer> evaluateAnswers(List<StudentAnswer> answers, List<Question> questions, Student student, Test test) {
        List<StudentAnswer> actualAnswers = new ArrayList<>();
        for (Question question : questions) {
            List<StudentAnswer> sans = new ArrayList<>();
            int numOfAnswers = adao.getAllCorrectAnswers(question.getQuestionID()).size();
            for (int i = 0; i < answers.size(); i++) {
                if (answers.get(i).getQuestion().getQuestionID() == question.getQuestionID()) {
                    sans.add(answers.remove(i));
                }
            }
            if (sans.size() == numOfAnswers) {
                boolean correct = true;
                for (StudentAnswer san : sans) {
                    correct = correct && san.isCorrectAns();
                }
                actualAnswers.add(new StudentAnswer(numOfAnswers, student, question, correct, test));
            } else {
                actualAnswers.add(new StudentAnswer(numOfAnswers, student, question, false, test));
            }
        }
        return actualAnswers;
    }
    
    public void setTotalMarks(TestAttempt testAttempt){
        int total = 0;
        List<StudentAnswer> answers = testAttempt.getAnswers();
        for (StudentAnswer answer : answers) {
            if(answer.isCorrectAns()){
                total += answer.getQuestion().getMarkAllocation();
            }
        }
        testAttempt.setTotalMarks(total);
    }

    public List<StudentAnswer> getAllStudentAnswersByQuestion() {
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
