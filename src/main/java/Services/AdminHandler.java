package Services;

import lombok.RequiredArgsConstructor;
import Services.ServicesInterfaces.AdminService;

import java.util.List;

@RequiredArgsConstructor
public class AdminHandler {

    private AdminService adminService;

    public void createModule(String moduleName, String moduleDescription) {
        if (moduleName != null) {
            adminService.createModule(moduleName, moduleDescription);
        }
    }

    public void modifyModule(int moduleID, String moduleName, String moduleDescription) {
        if (moduleID != 0) {
            adminService.modifyModule(moduleID, moduleName, moduleDescription);
        }
    }

    public void deleteModule(int moduleID) {
        if (moduleID != 0) {
            adminService.deleteModule(moduleID);
        }
    }

    public void createTest(String testName, int moduleID, List<Integer> topicIDs, int numQuestions, int numCorrectAnswersPerQuestion, int timeAllocation) {
        adminService.createTest(testName, moduleID, topicIDs, numQuestions, numCorrectAnswersPerQuestion, timeAllocation);
    }

    public void modifyTest(int testID, String testName, int moduleID, List<Integer> topicIDs, int numQuestions, int numCorrectAnswersPerQuestion, int timeAllocation) {
        if (testID != 0) {
            adminService.modifyTest(testID, testName, moduleID, topicIDs, numQuestions, numCorrectAnswersPerQuestion, timeAllocation);
        }
    }

    public void deleteTest(int testID) {
        if (testID != 0) {
            adminService.deleteTest(testID);
        }
    }

    public void manageAdminAccount(int adminID, boolean block) {
        if (adminID != 0) {
            adminService.manageAdminAccount(adminID, block);
        }
    }

    public void suspendStudentAccount(int studentID, int duration, String reason) {
        if (studentID != 0) {
            adminService.suspendStudentAccount(studentID, duration, reason);
        }
    }

    public void defineTestWeight(int testID, double weight) {
        if (testID != 0) {
            adminService.defineTestWeight(testID, weight);
        }
    }

    public void viewOverallPerformanceData() {
        adminService.viewOverallPerformanceData();
    }

    public void generateReports() {
        adminService.generateReports();
    }
}
