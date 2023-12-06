package Services.ServicesInterfaces;

import Models.Reports.AdminReport;
import java.util.Optional;

public interface AdminReportService {

    Optional<AdminReport> getAdminReport(int reportID);

    boolean addAdminReport(AdminReport adminReport);

    boolean updateAdminReport(int reportID, AdminReport adminReport);

    boolean deleteAdminReport(int reportID);
}
