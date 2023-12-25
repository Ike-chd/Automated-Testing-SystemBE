package Models.Communication;

import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuspensionRequest {

    private int ssId;
    private Student student;
    private FacultyMember requestedBy;
    private Admin confirmedBy;
    private long start; 
    private int duration;
    private boolean active;
    private String reason;
}
