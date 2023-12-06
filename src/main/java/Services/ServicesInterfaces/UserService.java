package Services.ServicesInterfaces;

import Models.Users.User;
import java.util.Optional;

public interface UserService {

    public boolean addAccountRequest(User student);

    public Optional<User> getUser(User user);
    
    public Optional<User> getUserByEmial(String email);
    
    public boolean addAccount(User user);
}