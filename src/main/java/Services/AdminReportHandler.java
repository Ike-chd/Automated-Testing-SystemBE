package Services;

import Models.Reports.AdminReport;
import Services.ServicesInterfaces.AdminReportService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AdminReportHandler implements AdminReportService {

    private static final Map<Integer, AdminReport> adminReportDatabase = new HashMap<>();

    @Override
    public Optional<AdminReport> getAdminReport(int reportID) {
        return Optional.ofNullable(adminReportDatabase.get(reportID));
    }

    @Override
    public boolean addAdminReport(AdminReport adminReport) {
        if (!adminReportDatabase.containsKey(adminReport.getReportID())) {
            adminReportDatabase.put(adminReport.getReportID(), adminReport);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAdminReport(int reportID, AdminReport updatedAdminReport) {
        if (adminReportDatabase.containsKey(reportID)) {
            adminReportDatabase.put(reportID, updatedAdminReport);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAdminReport(int reportID) {
        if (adminReportDatabase.containsKey(reportID)) {
            adminReportDatabase.remove(reportID);
            return true;
        }
        return false;
    }
}
