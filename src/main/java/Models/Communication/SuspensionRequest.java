package Models.Communication;

import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuspensionRequest {

    Student student;
    FacultyMember requestedBy;
    Admin confirmedBy;
    Calendar start;
    Calendar end;
}
