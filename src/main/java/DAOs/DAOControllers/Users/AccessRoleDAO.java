package DAOs.DAOControllers.Users;

import Models.Users.AccessRole;

import java.util.List;

public interface AccessRoleDAO {

    AccessRole getAccessRole(int accessRoleId);

    boolean insertAccessRole(AccessRole accessRole);

    boolean updateAccessRole(AccessRole accessRole);

    boolean deleteAccessRole(int accessRole);

    List<AccessRole> getAccessRoles();
}
