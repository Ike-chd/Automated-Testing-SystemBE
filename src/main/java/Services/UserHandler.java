package Services;

import DAOs.DAOControllers.Users.StudentDAO;
import DAOs.DAOControllers.Users.UserDAO;
import DAOs.StudentDB;
import DAOs.UserDB;
import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.User;
import Services.ServicesInterfaces.UserService;
import java.util.List;
import java.util.Optional;

public class UserHandler implements UserService {

    UserDAO udao = new UserDB();
    StudentDAO sdao = new StudentDB();

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

    @Override
    public Optional<User> getUserByEmial(String email) {
        User user = null;
        if(checkIfEmailExists(email)) {
            if((user = udao.getUserByEmail(email)) == null) {
                user = sdao.getStudentByEmail(email);
                return Optional.ofNullable(user);
            }
            return Optional.ofNullable(user);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public boolean addAccount(User user) {
        return udao.insertUser(user);
    }
    
    @Override
    public List<FacultyMember> getAllFacM(){
        return udao.getAllFacM();
    }
    
    @Override
    public List<Admin> getAllAdmins(){
        return udao.getAllAdmin();
    }
}