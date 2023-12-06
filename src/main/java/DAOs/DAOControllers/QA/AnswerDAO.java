package DAOs.DAOControllers.QA;

import Models.QA.Answer;
import Models.QA.Question;
import java.util.List;

public interface AnswerDAO {

    public Answer getAnswer(int answerI);

    public boolean insertAnswer(Answer answer);

    public boolean updateAnswer(Answer answer);

    public boolean deleteAnswer(Answer answer);

    public List<Answer> allQuestionAnswers(Question question);
}
