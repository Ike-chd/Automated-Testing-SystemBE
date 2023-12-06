package Services;

import Models.SuspensionRequest.SuspensionRequest;
import Services.ServicesInterfaces.SuspensionRequestService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SuspensionRequestHandler implements SuspensionRequestService {

    private static final Map<Integer, SuspensionRequest> suspensionRequestDatabase = new HashMap<>();

    @Override
    public Optional<SuspensionRequest> getSuspensionRequest(int ssid) {
        return Optional.ofNullable(suspensionRequestDatabase.get(ssid));
    }

    @Override
    public boolean addSuspensionRequest(SuspensionRequest suspensionRequest) {
        if (!suspensionRequestDatabase.containsKey(suspensionRequest.getSsId())) {
            suspensionRequestDatabase.put(suspensionRequest.getSsId(), suspensionRequest);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateSuspensionRequest(int ssid, SuspensionRequest updatedSuspensionRequest) {
        if (suspensionRequestDatabase.containsKey(ssid)) {
            suspensionRequestDatabase.put(ssid, updatedSuspensionRequest);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSuspensionRequest(int ssid) {
        if (suspensionRequestDatabase.containsKey(ssid)) {
            suspensionRequestDatabase.remove(ssid);
            return true;
        }
        return false;
    }
}
