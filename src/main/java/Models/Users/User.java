package Models.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class User {

    private int userID;
    private String name, surname, email, idNumber, password;
    private AccessRole accessRole;

    public User(int userID, String name, String surname, String email, String idNumber, String password, AccessRole accessRole) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.idNumber = idNumber;
        this.password = password;
        this.accessRole = accessRole;
    }
}
