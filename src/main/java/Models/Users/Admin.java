package Models.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin extends User {

    private boolean isSuperAdmin;

    public Admin(int userID, String name, String surname, String email, String idNumber, String password, AccessRole accessRole) {
        super(userID, name, surname, email, idNumber, password, accessRole);
    }

    public Admin(int userId, String firstname, String surname, String email, String idNumber, String password, int accessRoleID) {
    }
}
