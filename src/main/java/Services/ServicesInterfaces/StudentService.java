package Services.ServicesInterfaces;

import Models.QA.Question;
import Models.Tests.Test;

public interface StudentService {

    public void viewReport();

    public void viewHardestTopic();

    public void viewTestMarks();

    public void takeTest(Test test);

    public void bookmarkQuestion(Question question);

    public void provideTestRating(Test test, double rating);
}
