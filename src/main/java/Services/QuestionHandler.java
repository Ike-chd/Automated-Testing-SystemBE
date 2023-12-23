package Services;

import DAOs.AnswerDB;
import DAOs.DAOControllers.QA.AnswerDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.QuestionDB;
import Models.QA.Question;
import Services.ServicesInterfaces.QuestionService;
import java.util.List;
import java.util.Optional;

public class QuestionHandler implements QuestionService {

    QuestionDAO qdao = new QuestionDB();
    AnswerDAO adao = new AnswerDB();

    @Override
    public Optional<Question> getQuestion(int questionId) {
        return Optional.ofNullable(qdao.getQuestion(questionId));
    }

    @Override
    public boolean addQuestion(Question question) {
        return qdao.insertQuestion(question);
    }

    @Override
    public boolean updateQuestion(Question question) {
        return qdao.updateQuestion(question);
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        if (adao.deleteAnswer(adao.getAnswerByQuestionID(questionId))) {
            return qdao.deleteQuestion(questionId);
        }
        return false;
    }

    @Override
    public List<Question> getAllQuestionsUnderTopic(int topicId) {
        return qdao.allQuestionUnderATopic(topicId);
    }

}
