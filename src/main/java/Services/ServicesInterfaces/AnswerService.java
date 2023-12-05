package Services.ServicesInterfaces;

import Models.QA.Answer;

import java.util.Optional;

public interface AnswerService {
    
    Optional<Answer> getAnswer(int answerID);

    boolean addAnswer(Answer answer);

    boolean updateAnswer(int answerID, Answer answer);

    boolean deleteAnswer(int answerID);
}
