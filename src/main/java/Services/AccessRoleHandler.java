package Services;

import Models.Users.AccessRole;
import Services.ServicesInterfaces.AccessRoleService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AccessRoleHandler implements AccessRoleService {

    private static final Map<Integer, AccessRole> accessRoleDatabase = new HashMap<>();

    @Override
    public Optional<AccessRole> getAccessRole(int accessRoleID) {
        return Optional.ofNullable(accessRoleDatabase.get(accessRoleID));
    }

    @Override
    public boolean addAccessRole(AccessRole accessRole) {
        if (!accessRoleDatabase.containsKey(accessRole.getAccessRoleID())) {
            accessRoleDatabase.put(accessRole.getAccessRoleID(), accessRole);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateAccessRole(int accessRoleID, AccessRole updatedAccessRole) {
        if (accessRoleDatabase.containsKey(accessRoleID)) {
            accessRoleDatabase.put(accessRoleID, updatedAccessRole);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccessRole(int accessRoleID) {
        if (accessRoleDatabase.containsKey(accessRoleID)) {
            accessRoleDatabase.remove(accessRoleID);
            return true;
        }
        return false;
    }
}
