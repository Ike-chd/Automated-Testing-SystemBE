package DAOs.DAOControllers.QA;

import Models.Courses.Topic;
import Models.QA.Question;
import java.util.List;

public interface QuestionDAO {
    public Question getQuestion();
    public Question getQuestion(int id);
    public boolean insertQuestion(Question question);
    public boolean deleteQuestion(Question question);
    public boolean updateQuestion(Question question);
    public List<Question> allQuestionUnderATopic(Topic topic);
}
