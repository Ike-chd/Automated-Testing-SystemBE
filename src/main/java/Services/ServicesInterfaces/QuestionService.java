package Services.ServicesInterfaces;

import Models.QA.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {

    public Optional<Question> getQuestion(int questionId);

    public boolean addQuestion(Question question);

    public boolean updateQuestion(Question question);

    public boolean deleteQuestion(int questionId);
    
    public List<Question> getAllQuestionsUnderTopic(int topicId);
}
