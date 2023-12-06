
package Services.ServicesInterfaces;

import Models.Communication.SuspensionRequest;

import java.util.Optional;

public interface SuspensionRequestService {
    Optional<SuspensionRequest> getSuspensionRequest(int ssId);
    boolean addSuspensionRequest(SuspensionRequest request);
    boolean deleteSuspensionRequest(SuspensionRequest request);
    boolean updateSuspensionRequest(SuspensionRequest request);
}
