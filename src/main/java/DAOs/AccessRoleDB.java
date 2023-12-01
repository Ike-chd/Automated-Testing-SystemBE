package DAOs;

import DAOs.DAOControllers.Users.AccessRoleDAO;
import DBConnection.DBConnection;
import Models.Users.AccessRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccessRoleDB implements AccessRoleDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private DBConnection connection;
    private Statement s;
    
    @Override
    public AccessRole getAccessRole(int accessRoleId) {
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM AccessRoles WHERE accessRoleID = ?");
            ps.setInt(1,accessRoleId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return extractAccessRoleFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
        return null;
    }

    @Override
    public boolean insertAccessRole(AccessRole accessRole) {
        try {
            ps = connection.getConnection().prepareStatement("INSERT INTO AccessRoles (accessRole) VALUES (?)");
            ps.setString(1,accessRole.getRoleName());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean updateAccessRole(AccessRole accessRole) {
        try {
            ps = connection.getConnection().prepareStatement("UPDATE AccessRoles SET accessRole = ? WHERE accessRoleID = ?");
            ps.setString(1, accessRole.getRoleName());
            ps.setInt(2,accessRole.getRoleId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean deleteAccessRole(AccessRole accessRole) {
        try {
            ps = connection.getConnection().prepareStatement("DELETE FROM AccessRoles WHERE accessRoleID = ?");
            ps.setInt(1,accessRole.getRoleId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            
        }
        return false;
    }

    @Override
    public List<AccessRole> getAccessRoles() {
        List<AccessRole> accessRoles = new ArrayList<>();
        try {
            s = connection.getConnection().createStatement();
            rs = s.executeQuery("SELECT * FROM AccessRoles");
            while(rs.next()){
                AccessRole accessRole = extractAccessRoleFromResultSet(rs);
                accessRoles.add(accessRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            
        }
        return accessRoles;
    }

    private AccessRole extractAccessRoleFromResultSet(ResultSet resultSet) throws SQLException {
        int accessRoleId = resultSet.getInt("accessRoleID");
        String accessRole = resultSet.getString("accessRole");
        return new AccessRole(accessRoleId,accessRole);
    }
}
