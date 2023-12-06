package Services.ServicesInterfaces;

import Models.Communication.Comment;
import Models.Courses.Course;
import Models.Users.FacultyMember;
import Models.Users.Student;

import java.util.List;
import java.util.Optional;

public interface FacultyMemberService {

    Optional<FacultyMember> getFacultyMemberById(int facultyId);

    List<FacultyMember> getAllFacultyMembers();

    boolean insertFacultyMember(FacultyMember facultyMember);

    boolean updateFacultyMember(FacultyMember facultyMember);

    boolean deleteFacultyMember(FacultyMember facultyMember);
}
