package Services.ServicesInterfaces;

import Models.Communication.SuspensionRequest;
import java.util.List;
import java.util.Optional;

public interface SuspensionRequestService {

    public Optional<SuspensionRequest> getSuspensionRequest(int ssid);

    public boolean addSuspensionRequest(SuspensionRequest suspensionRequest);

    public boolean updateSuspensionRequest(SuspensionRequest suspensionRequest);

    public boolean deleteSuspensionRequest(int ssid);

    public List<SuspensionRequest> getAllPendingRequests();

    public List<SuspensionRequest> getAllActiveRequests();

    public List<SuspensionRequest> getAllRequests();
}
