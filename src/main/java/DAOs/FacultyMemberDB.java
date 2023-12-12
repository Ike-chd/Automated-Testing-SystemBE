package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.AccessRoleDAO;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DBConnection.DBConnection;
import Models.Users.FacultyMember;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultyMemberDB extends DBConnection implements FacultyMemberDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private AccessRoleDAO adao = new AccessRoleDB();

    @Override
    public FacultyMember getFacultyMember(int userId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE userID = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractFacultyMemberFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addFacultyMember(FacultyMember facultyMember) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Users (firstname, surname, email, idNumber, password) VALUES (?,?,?,?,?)");
            ps.setString(1, facultyMember.getName());
            ps.setString(2, facultyMember.getSurname());
            ps.setString(3, facultyMember.getEmail());
            ps.setString(4, facultyMember.getIdNumber());
            ps.setString(5, facultyMember.getPassword());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean updateFacultyMember(FacultyMember facultyMember) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Users SET firstname = ?, surname = ?, email = ?, idNumber = ?, password = ? WHERE userID = ?");
            ps.setString(1, facultyMember.getName());
            ps.setString(2, facultyMember.getSurname());
            ps.setString(3, facultyMember.getEmail());
            ps.setString(4, facultyMember.getIdNumber());
            ps.setString(5, facultyMember.getPassword());
            ps.setInt(6, facultyMember.getUserID());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteFacultyMember(FacultyMember facultyMember)  {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Users WHERE userID = ?");
            ps.setInt(1, facultyMember.getUserID());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected == 1 ;
    }

    @Override
    public List<FacultyMember> getAllFacultyMembers() {
        List<FacultyMember> facultyMembers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE accessRole = 3");
            rs = ps.executeQuery();
            while (rs.next()){
                facultyMembers.add(extractFacultyMemberFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return facultyMembers;
    }

    private FacultyMember extractFacultyMemberFromResultSet(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("userID");
        String firstname = resultSet.getString("firstname");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String idNumber = resultSet.getString("idNumber");
        String password = resultSet.getString("password");
        int accessRoleID = resultSet.getInt("accessRoleID");
        return new FacultyMember(userID,firstname,surname,email,idNumber,password,adao.getAccessRole(accessRoleID));
    }
}