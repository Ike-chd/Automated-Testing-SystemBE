package Models.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class FacultyMember extends User {

    private boolean isProfessor;

    public FacultyMember(int userID, String name, String surname, String email, String idNumber, String password, AccessRole accessRole) {
        super(userID, name, surname, email, idNumber, password, accessRole);
    }

    public FacultyMember(int userID, String name, String surname, String email, String idNumber, AccessRole accessRole) {
        super(userID, name, surname, email, idNumber, accessRole);
    }


    public void setIsProfessor(boolean isProfessor) {
        this.isProfessor = isProfessor;
    }

    public boolean isProfessor() {
        return isProfessor;
    }
}
