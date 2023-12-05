package Services;

import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DAOs.FacultyMemberDB;
import Models.Users.FacultyMember;
import Services.ServicesInterfaces.FacultyMemberService;

import java.util.List;
import java.util.Optional;

public class FacultyMemberHandler implements FacultyMemberService {

    private FacultyMemberDAO fdao = new FacultyMemberDB();

    @Override
    public Optional<FacultyMember> getFacultyMemberById(int facultyId) {
        return Optional.ofNullable(fdao.getFacultyMember(facultyId));
    }

    @Override
    public List<FacultyMember> getAllFacultyMembers() {
        return fdao.getAllFacultyMembers();
    }

    @Override
    public boolean insertFacultyMember(FacultyMember facultyMember) {
        return fdao.addFacultyMember(facultyMember);
    }

    @Override
    public boolean updateFacultyMember(FacultyMember facultyMember) {
        return fdao.updateFacultyMember(facultyMember);
    }

    @Override
    public boolean deleteFacultyMember(FacultyMember facultyMember) {
        return fdao.deleteFacultyMember(facultyMember);
    }
}
