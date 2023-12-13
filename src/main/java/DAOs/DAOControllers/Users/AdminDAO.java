package DAOs.DAOControllers.Users;

import Models.Users.Admin;

import java.util.List;

public interface AdminDAO {
    Admin getAdmin(int userId);
    boolean addAdmin(Admin admin);
    boolean removeAdmin(int userId);
    boolean updateAdmin(Admin admin);
    List<Admin> getAllAdmins();
}
