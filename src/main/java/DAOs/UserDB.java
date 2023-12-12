package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.UserDAO;
import DBConnection.DBConnection;
import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB extends DBConnection implements UserDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public boolean insertUser(User user) {
        int updated = 0;
        int accessRoleID = 0;
        try {
            if(user instanceof FacultyMember && ((FacultyMember)user).isProfessor()){
                accessRoleID = 1;
            } else if(user instanceof FacultyMember){
                accessRoleID = 3;
            } else if(user instanceof Admin && ((Admin)user).isSuperAdmin()){
                accessRoleID = 4;
            } else{
                accessRoleID = 2;
            }
            ps = getConnection().prepareStatement("INSERT INTO users(firstname, surname, email, idNumber, password, accessRoleID)"
                    + "VALUES(?,?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getIdNumber());
            ps.setString(5, user.getPassword());
            ps.setInt(6, accessRoleID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated == 1;
    }

    @Override
    public void updateUser(User user) {
        try {
            ps = getConnection().prepareStatement("UPDATE users SET firstname=?, surname=?, email=?, idNumber=?, password=? WHERE userId=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getIdNumber());
            ps.setString(5, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteUser(int userID) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM users WHERE userId=?");
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUser(int userId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM users "
                    + "WHERE userId = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromDB(rs);
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean createAccountReq(User student) {
        int updated = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO accountreq(name, surname, email, idNumber) "
                    + "VALUES(?, ?, ?, ?)");
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getIdNumber());
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return updated == 1;
    }

    @Override
    public User getUser(User user) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM users "
                    + "WHERE userId = ?");
            ps.setInt(1, user.getUserID());
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromDB(rs);
            }
            return null;
        } catch (SQLException ex) {
           ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    public User extractUserFromDB(ResultSet rs) {
        User user = new User();
        return user;
    }

    @Override
    public boolean checkForEmail(String email) {
        try {
            ps = getConnection().prepareStatement("SELECT email FROM users "
                    + "WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM users "
                    + "WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractUserFromDB(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
