package Services.ServicesInterfaces;

import Models.SuspensionRequest.SuspensionRequest;

import java.util.Optional;

public interface SuspensionRequestService {

    Optional<SuspensionRequest> getSuspensionRequest(int ssid);

    boolean addSuspensionRequest(SuspensionRequest suspensionRequest);

    boolean updateSuspensionRequest(int ssid, SuspensionRequest suspensionRequest);

    boolean deleteSuspensionRequest(int ssid);
}
