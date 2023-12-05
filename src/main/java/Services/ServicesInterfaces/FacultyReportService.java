package Services.ServicesInterfaces;

import Models.Reports.FacultyReport;

import java.util.Optional;

public interface FacultyReportService {

    Optional<FacultyReport> getFacultyReport(int reportID);

    boolean addFacultyReport(FacultyReport facultyReport);

    boolean updateFacultyReport(int reportID, FacultyReport facultyReport);

    boolean deleteFacultyReport(int reportID);
}
