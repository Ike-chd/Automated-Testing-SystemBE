package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Users.AccessRoleDAO;
import DBConnection.DBConnection;
import Models.Users.AccessRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class AccessRoleDB extends DBConnection implements AccessRoleDAO {
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public AccessRole getAccessRole(int accessRoleId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM AccessRoles WHERE accessRoleID = ?");
            ps.setInt(1,accessRoleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractAccessRoleFromResultSet(rs);
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
    public boolean insertAccessRole(AccessRole accessRole) {
        try {
            ps = getConnection().prepareStatement("INSERT INTO AccessRoles (accessRole) VALUES (?)");
            ps.setString(1, accessRole.getRoleName());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean updateAccessRole(AccessRole accessRole) {
        try {
            ps = getConnection().prepareStatement("UPDATE AccessRoles SET accessRole = ? WHERE accessRoleID = ?");
            ps.setString(1, accessRole.getRoleName());
            ps.setInt(2, accessRole.getAccessRoleID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean deleteAccessRole(AccessRole accessRole) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM AccessRoles WHERE accessRoleID = ?");
            ps.setInt(1, accessRole.getAccessRoleID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<AccessRole> getAccessRoles() {
        List<AccessRole> accessRoles = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM AccessRoles");
            rs = ps.executeQuery();
            while (rs.next()) {
                accessRoles.add(extractAccessRoleFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return accessRoles;
    }

    private AccessRole extractAccessRoleFromResultSet(ResultSet resultSet) throws SQLException {
        int accessRoleId = resultSet.getInt("accessRoleID");
        String accessRole = resultSet.getString("accessRole");
        return new AccessRole(accessRoleId, accessRole);
    }
}
