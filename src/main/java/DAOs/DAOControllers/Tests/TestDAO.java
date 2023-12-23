package DAOs.DAOControllers.Tests;

import Models.Tests.Test;
import Models.QA.Question;
import java.util.List;

public interface TestDAO {

    public Test getTest(int id);

    public boolean insetTest(Test test);

    public boolean deleteTest(int testId);

    public boolean updateTest(Test test);

    List<Test> getAllTests();
    
    public List<Test> getAllTestsByModuleID(int moduleID);

    public int getLastInsertID();

    public void insertTestQuestions(int id, int questionID);
    
    public List<Question> getAllQuestionsInTest(int testId);
}
