package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.AdminDAO;
import DBConnection.DBConnection;
import Models.Tests.Test;
import Models.Users.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDB extends DBConnection implements AdminDAO {
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Admin getAdmin(int userId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE userID = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return extractAdminFromResultSet(rs);
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
        return null;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Users (firstname,surname,email,idNumber,password,isSuperAdmin) VALUES(?,?,?,?,?,?)");
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getSurname());
            ps.setString(3, admin.getEmail());
            ps.setString(4,admin.getIdNumber());
            ps.setString(5,admin.getPassword());
            ps.setBoolean(6,admin.isSuperAdmin());
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean removeAdmin(int userId) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Users WHERE userId = ?");
            ps.setInt(1, userId);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Users SET firstname = ?, surname = ?, email = ?, idNumber = ?, password = ?, isSuperAdmin = ?");
        } catch (SQLException ex) {
            Logger.getLogger(AdminDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Admin> getAllAdmins() {
        return null;
    }
    private Admin extractAdminFromResultSet(ResultSet resultSet){
          return null;
    }
}
