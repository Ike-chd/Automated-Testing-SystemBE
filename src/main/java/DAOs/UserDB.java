package DAOs;

import DAOs.DAOControllers.Users.UserDAO;
import DBConnection.DBConnection;
import Models.Users.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB extends DBConnection implements UserDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    
    @Override
    public void insertUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUser(int userID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User getUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
        }
        return updated == 1;
    }

    @Override
    public User getUser(User user) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM users "
                    + "WHERE username = ?");
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();
            if(rs.next()){
                return extractUserFromDB(rs);
            }
            return null;    
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            
        }
    }
    
    public User extractUserFromDB(ResultSet rs){
        return null;
    }
}