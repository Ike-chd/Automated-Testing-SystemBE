package Services.ServicesInterfaces;

import Models.Communication.SuspensionRequest;
import Models.Users.Student;
import java.util.List;

public interface SuspensionRequestService {

    SuspensionRequest getSuspensionRequest(int ssId);

    boolean insertSuspensionRequest(SuspensionRequest ssRequest);

    boolean updateSuspensionRequest(SuspensionRequest ssRequest);
    
    public boolean confirmSuspensionRequest(int ssID, int conID, int con);

    boolean deleteSuspensionRequest(int ssId);

    List<SuspensionRequest> getAllSuspensionRequests();

    List<SuspensionRequest> getAllActiveSuspensionRequests();

    List<SuspensionRequest> getSuspensionRequestsByStudent(Student student);
    
    List<SuspensionRequest> getAllUnApproved();
}
