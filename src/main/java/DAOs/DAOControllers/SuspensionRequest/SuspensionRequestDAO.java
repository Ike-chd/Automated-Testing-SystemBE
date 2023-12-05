package DAOs.DAOControllers.SuspensionRequest;

import Models.SuspensionRequest.SuspensionRequest;
import Models.Users.Student;

import java.util.List;

public interface SuspensionRequestDAO {

    SuspensionRequest getSuspensionRequest(int ssId);

    boolean insertSuspensionRequest(SuspensionRequest ssRequest);

    boolean updateSuspensionRequest(SuspensionRequest ssRequest);

    boolean deleteSuspensionRequest(SuspensionRequest ssRequest);

    List<SuspensionRequest> getAllSuspensionRequests();

    List<SuspensionRequest> getAllActiveSuspensionRequests();

    List<SuspensionRequest> getSuspensionRequestsByStudent(Student student);
}
