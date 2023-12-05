package DAOs.DAOControllers.Users;

import Models.Users.FacultyMember;
import java.util.List;

public interface FacultyMemberDAO {

    public FacultyMember getFacultyMember(int userId);

    public boolean addFacultyMember(FacultyMember facultyMember);

    public boolean updateFacultyMember(FacultyMember facultyMember);

    public boolean deleteFacultyMember(FacultyMember facultyMember);

    public List<FacultyMember> getAllFacultyMembers();
}
