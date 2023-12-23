package Services.ServicesInterfaces;

import Models.QA.Answer;
import java.util.List;

import java.util.Optional;

public interface AnswerService {
    
    public Optional<Answer> getAnswer(int answerID);

    public boolean addAnswer(Answer answer);
    
    public boolean addAnswers(List<Answer> answers);

    public boolean updateAnswer(Answer answer);

    public boolean deleteAnswer(int answerID);
    
    public List<Answer> getAllAnsByQues(int questionId);
}
