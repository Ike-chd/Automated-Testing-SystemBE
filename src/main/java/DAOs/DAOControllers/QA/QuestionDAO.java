package DAOs.DAOControllers.QA;

import Models.Courses.Topic;
import Models.QA.Question;
import java.util.List;

public interface QuestionDAO {
    public Question getQuestion(int questionId);

    public boolean insertQuestion(Question question);

    public boolean deleteQuestion(int questionId);

    public boolean updateQuestion(Question question);

    public List<Question> allQuestionUnderATopic(int topicId);
    
    public List<Question> allQuestionsUnderATest(int id);
    
    public int getLastInsertID();
}
