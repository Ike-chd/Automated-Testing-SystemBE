package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DBConnection.DBConnection;
import Models.Users.FacultyMember;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacultyMemberDB extends DBConnection implements FacultyMemberDAO {

    private PreparedStatement ps;
    private ResultSet rs;

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
        try {
            ps = getConnection().prepareStatement("INSERT INTO Users (firstname, surname, email, idNumber, password) VALUES (?,?,?,?,?)");
            ps.setString(1, facultyMember.getName());
            ps.setString(2, facultyMember.getSurname());
            ps.setString(3, facultyMember.getEmail());
            ps.setString(4, facultyMember.getIdNumber());
            ps.setString(5, facultyMember.getPassword());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateFacultyMember(FacultyMember facultyMember) {
        try {
            ps = getConnection().prepareStatement("UPDATE Users SET firstname = ?, surname = ?, email = ?, idNumber = ?, password = ? WHERE userID = ?");
            ps.setString(1, facultyMember.getName());
            ps.setString(2, facultyMember.getSurname());
            ps.setString(3, facultyMember.getEmail());
            ps.setString(4, facultyMember.getIdNumber());
            ps.setString(5, facultyMember.getPassword());
            ps.setInt(6, facultyMember.getUserID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteFacultyMember(FacultyMember facultyMember)  {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Users WHERE userID = ?");
            ps.setInt(1, facultyMember.getUserID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<FacultyMember> getAllFacultyMembers() {
        return null;
    }

    private FacultyMember extractFacultyMemberFromResultSet(ResultSet resultSet) {
        return null;
    }
}