package Services;

import DAOs.DAOControllers.Users.UserDAO;
import DAOs.UserDB;
import Models.Users.User;
import Services.ServicesInterfaces.UserService;
import java.util.Optional;

public class UserHandler implements UserService {

    UserDAO udao = new UserDB();

    @Override
    public boolean addAccountRequest(User student) {
        if (checkIfEmailExists(student.getEmail())) {
            return false;
        }
        return udao.createAccountReq(student);
    }

    @Override
    public Optional<User> getUser(User user) {
        return Optional.ofNullable(udao.getUser(user));
    }

    public boolean checkIfEmailExists(String string) {
        return udao.checkForEmail(string);
    }
}
