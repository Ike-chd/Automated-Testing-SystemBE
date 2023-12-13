package Services.ServicesInterfaces;

import Models.QA.Question;
import java.util.Optional;

public interface QuestionService {

    public Optional<Question> getQuestion(int questionId);

    public boolean addQuestion(Question question);

    public boolean updateQuestion(Question question);

    public boolean deleteQuestion(int questionId);
}
