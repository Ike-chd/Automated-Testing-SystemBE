package Services;

import Models.Reports.FacultyReport;
import Services.ServicesInterfaces.FacultyReportService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FacultyReportHandler implements FacultyReportService {

    private static final Map<Integer, FacultyReport> facultyReportDatabase = new HashMap<>();

    @Override
    public Optional<FacultyReport> getFacultyReport(int reportID) {
        return Optional.ofNullable(facultyReportDatabase.get(reportID));
    }

    @Override
    public boolean addFacultyReport(FacultyReport facultyReport) {
        if (!facultyReportDatabase.containsKey(facultyReport.getReportID())) {
            facultyReportDatabase.put(facultyReport.getReportID(), facultyReport);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateFacultyReport(int reportID, FacultyReport updatedFacultyReport) {
        if (facultyReportDatabase.containsKey(reportID)) {
            facultyReportDatabase.put(reportID, updatedFacultyReport);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFacultyReport(int reportID) {
        if (facultyReportDatabase.containsKey(reportID)) {
            facultyReportDatabase.remove(reportID);
            return true;
        }
        return false;
    }
}
