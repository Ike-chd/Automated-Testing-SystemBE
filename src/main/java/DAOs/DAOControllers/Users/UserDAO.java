package DAOs.DAOControllers.Users;

import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.User;
import java.util.List;

public interface UserDAO {

    public boolean insertUser(User user);

    public void updateUser(User user);

    public void deleteUser(int userID);

    public User getUser(int userId);

    public User getUser(User user);

    public boolean createAccountReq(User student);

    public boolean checkForEmail(String email);
    
    public User getUserByEmail(String email);
    
    public List<FacultyMember> getAllFacM();
    
    public List<Admin> getAllAdmin();
    
    public User getUserForLogIn(int user);
}
