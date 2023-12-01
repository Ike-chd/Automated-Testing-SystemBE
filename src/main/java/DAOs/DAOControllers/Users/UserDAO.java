package DAOs.DAOControllers.Users;

import Models.Users.User;

public interface UserDAO {
    public void insertUser(User user);
    public void updateUser(User user);
    public void deleteUser(int userID);
    public User getUser(int userId);
    public User getUser(User user);
    public boolean createAccountReq(User student);
    public boolean checkForEmail(String email);
}