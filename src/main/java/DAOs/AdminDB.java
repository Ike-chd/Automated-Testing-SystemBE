package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.AccessRoleDAO;
import DAOs.DAOControllers.Users.AdminDAO;
import DBConnection.DBConnection;
import Models.Users.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDB extends DBConnection implements AdminDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private AccessRoleDAO adao = new AccessRoleDB();

    @Override
    public Admin getAdmin(int userId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE userID = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractAdminFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
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
            ps.setString(4, admin.getIdNumber());
            ps.setString(5, admin.getPassword());
            ps.setBoolean(6, admin.isSuperAdmin());
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
        return rowsAffected == 1;
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
        return rowsAffected == 1;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Users SET firstname = ?, surname = ?, email = ?, idNumber = ?, password = ?, isSuperAdmin = ? WHERE userID = ?");
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getSurname());
            ps.setString(3, admin.getEmail());
            ps.setString(4, admin.getIdNumber());
            ps.setString(5, admin.getPassword());
            ps.setBoolean(6, admin.isSuperAdmin());
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
        return rowsAffected == 1;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE accessRole = 2");
            rs = ps.executeQuery();
            while (rs.next()) {
                admins.add(extractAdminFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return admins;
    }

    private Admin extractAdminFromResultSet(ResultSet resultSet) throws SQLException {
        int userID = resultSet.getInt("userID");
        String name = resultSet.getString("firstname");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String idNumber = resultSet.getString("idNumber");
        String password = resultSet.getString("password");
        int accessRoleID = resultSet.getInt("accessRoleID");
        Admin admin = new Admin(userID, name, surname, email, idNumber, adao.getAccessRole(accessRoleID));
        admin.setPassword(password);
        return admin;
    }

    @Override
    public Admin getAdminForLogIn(int userId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Users WHERE userID = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int userID = rs.getInt("userID");
                String name = rs.getString("firstname");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                String idNumber = rs.getString("idNumber");
                String password = rs.getString("password");
                int accessRoleID = rs.getInt("accessRoleID");
                return new Admin(userID,name,surname,email,idNumber,password,adao.getAccessRole(accessRoleID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
