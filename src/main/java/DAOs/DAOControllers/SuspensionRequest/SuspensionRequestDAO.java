package DAOs.DAOControllers.SuspensionRequest;

import Models.Communication.SuspensionRequest;
import Models.Users.Student;

import java.util.List;

public interface SuspensionRequestDAO {

    SuspensionRequest getSuspensionRequest(int ssId);

    boolean insertSuspensionRequest(SuspensionRequest ssRequest);

    boolean updateSuspensionRequest(SuspensionRequest ssRequest);
    
    public boolean confirmSuspensionRequest(int ssid, int conid);
    
    public boolean updateActiveStatus(int ssID, boolean active);

    boolean deleteSuspensionRequest(int ssId);

    List<SuspensionRequest> getAllSuspensionRequests();

    List<SuspensionRequest> getAllActiveSuspensionRequests();

    List<SuspensionRequest> getSuspensionRequestsByStudent(Student student);
    
    List<SuspensionRequest> getAllUnApprovedSReq();
    
    public boolean checkForActiveSuspensionByStudent(int studentID);
}
