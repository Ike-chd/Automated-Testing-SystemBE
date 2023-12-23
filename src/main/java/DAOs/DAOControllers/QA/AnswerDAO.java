package DAOs.DAOControllers.QA;

import Models.QA.Answer;
import Models.QA.Question;
import java.util.List;

public interface AnswerDAO {

    public Answer getAnswer(int answerI);

    public boolean insertAnswer(Answer answer);

    public boolean[] insertAnswers(List<Answer> answers);

    public boolean updateAnswer(Answer answer);

    public boolean deleteAnswer(int id);

    public List<Answer> allQuestionAnswers(int questionId);

    public int getAnswerByQuestionID(int questionID);

    public List<Answer> getAllCorrectAnswers(int questionId);
}
