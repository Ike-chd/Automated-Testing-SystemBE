package Services.ServicesInterfaces;

import java.util.List;

public interface AdminService {

    void createModule(String moduleName, String moduleDescription);

    void modifyModule(int moduleID, String moduleName, String moduleDescription);

    void deleteModule(int moduleID);

    void createTest(String testName, int moduleID, List<Integer> topicIDs, int numQuestions, int numCorrectAnswersPerQuestion, int timeAllocation);

    void modifyTest(int testID, String testName, int moduleID, List<Integer> topicIDs, int numQuestions, int numCorrectAnswersPerQuestion, int timeAllocation);

    void deleteTest(int testID);

    void manageAdminAccount(int adminID, boolean block);

    void suspendStudentAccount(int studentID, int duration, String reason);

    void defineTestWeight(int testID, double weight);

    void viewOverallPerformanceData();

    void generateReports();
}
