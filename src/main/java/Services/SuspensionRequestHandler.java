package Services;

import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import DAOs.SuspensionRequestDB;
import Models.Communication.SuspensionRequest;
import Services.ServicesInterfaces.SuspensionRequestService;

import java.util.Optional;

public class SuspensionRequestHandler implements SuspensionRequestService {
    private SuspensionRequestDAO dao = new SuspensionRequestDB();
    @Override
    public Optional<SuspensionRequest> getSuspensionRequest(int ssId) {
        return Optional.ofNullable(dao.getSuspensionRequest(ssId));
    }
    @Override
    public boolean addSuspensionRequest(SuspensionRequest request) {
        return dao.insertSuspensionRequest(request);
    }
    @Override
    public boolean deleteSuspensionRequest(SuspensionRequest request) {
        return dao.deleteSuspensionRequest(request);
    }
    @Override
    public boolean updateSuspensionRequest(SuspensionRequest request) {
        return dao.updateSuspensionRequest(request);
    }
}
