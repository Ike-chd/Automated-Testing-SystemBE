package Services;

import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.QuestionDB;
import Models.QA.Question;
import Services.ServicesInterfaces.QuestionService;
import java.util.Optional;

public class QuestionHandler implements QuestionService {

    QuestionDAO qdao = new QuestionDB();

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
        return qdao.deleteQuestion(questionId);
    }

}
