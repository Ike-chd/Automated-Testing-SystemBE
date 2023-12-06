package Services;

import DAOs.AnswerDB;
import DAOs.DAOControllers.QA.AnswerDAO;
import Models.QA.Answer;
import Services.ServicesInterfaces.AnswerService;

import java.util.List;
import java.util.Optional;

public class AnswerHandler implements AnswerService {

    AnswerDAO adao = new AnswerDB();

    @Override
    public Optional<Answer> getAnswer(int answerID) {
        return Optional.ofNullable(adao.getAnswer(answerID));
    }

    @Override
    public boolean addAnswer(Answer answer) {
        return adao.insertAnswer(answer);
    }

    @Override
    public boolean updateAnswer(Answer updatedAnswer) {
        return adao.updateAnswer(updatedAnswer);
    }

    @Override
    public boolean deleteAnswer(int answerID) {
        return adao.deleteAnswer(answerID);
    }

    @Override
    public boolean addAnswers(List<Answer> answers) {
        boolean allEntered = true;
        for (Answer answer : answers) {
            allEntered = allEntered && adao.insertAnswer(answer);
        }
        return allEntered;
    }
}
