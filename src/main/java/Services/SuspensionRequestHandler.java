package Services;

import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import Models.Communication.SuspensionRequest;
import Models.Users.Student;
import Services.ServicesInterfaces.SuspensionRequestService;

import java.util.List;

public class SuspensionRequestHandler implements SuspensionRequestService {

    private SuspensionRequestDAO suspensionRequestDAO;

    public SuspensionRequestHandler(SuspensionRequestDAO suspensionRequestDAO) {
        this.suspensionRequestDAO = suspensionRequestDAO;
    }

    @Override
    public SuspensionRequest getSuspensionRequest(int ssId) {
        return suspensionRequestDAO.getSuspensionRequest(ssId);
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        return suspensionRequestDAO.insertSuspensionRequest(ssRequest);
    }

    @Override
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        return suspensionRequestDAO.updateSuspensionRequest(ssRequest);
    }

    @Override
    public boolean deleteSuspensionRequest(int ssId) {
        return suspensionRequestDAO.deleteSuspensionRequest(ssId);
    }

    @Override
    public List<SuspensionRequest> getAllSuspensionRequests() {
        return suspensionRequestDAO.getAllSuspensionRequests();
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        return suspensionRequestDAO.getAllActiveSuspensionRequests();
    }

    @Override
    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
        return suspensionRequestDAO.getSuspensionRequestsByStudent(student);
    }
}
