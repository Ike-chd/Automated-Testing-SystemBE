package Services.ServicesInterfaces;

import Models.Users.AccessRole;

import java.util.Optional;

public interface AccessRoleService {
    
    Optional<AccessRole> getAccessRole(int accessRoleID);

    boolean addAccessRole(AccessRole accessRole);

    boolean updateAccessRole(int accessRoleID, AccessRole accessRole);

    boolean deleteAccessRole(int accessRoleID);
}
