package Services;

import Models.QA.Answer;
import Services.ServicesInterfaces.AnswerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AnswerHandler implements AnswerService {

    private static final Map<Integer, Answer> answerDatabase = new HashMap<>();

    @Override
    public Optional<Answer> getAnswer(int answerID) {
        return Optional.ofNullable(answerDatabase.get(answerID));
    }

    @Override
    public boolean addAnswer(Answer answer) {
        if (!answerDatabase.containsKey(answer.getAnswerID())) {
            answerDatabase.put(answer.getAnswerID(), answer);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAnswer(int answerID, Answer updatedAnswer) {
        if (answerDatabase.containsKey(answerID)) {
            answerDatabase.put(answerID, updatedAnswer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAnswer(int answerID) {
        if (answerDatabase.containsKey(answerID)) {
            answerDatabase.remove(answerID);
            return true;
        }
        return false;
    }
}
