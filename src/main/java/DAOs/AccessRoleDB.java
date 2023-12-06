//package DAOs;
//
//import DAOs.CloseStreams.CloseStreams;
//import DAOs.DAOControllers.Users.AccessRoleDAO;
//import DBConnection.DBConnection;
//import Models.Users.AccessRole;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//<<<<<<< HEAD
//public class AccessRoleDB extends DBConnection implements AccessRoleDAO {
//=======
//public class AccessRoleDB implements AccessRoleDAO {
//
//>>>>>>> origin/ike2
//    private PreparedStatement ps;
//    private ResultSet rs;
//    private Statement s;
//
//    @Override
//    public AccessRole getAccessRole(int accessRoleId) {
//        try {
//<<<<<<< HEAD
//            ps = getConnection().prepareStatement("SELECT * FROM AccessRoles WHERE accessRoleID = ?");
//            ps.setInt(1,accessRoleId);
//=======
//            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM AccessRoles WHERE accessRoleID = ?");
//            ps.setInt(1, accessRoleId);
//>>>>>>> origin/ike2
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return extractAccessRoleFromResultSet(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//<<<<<<< HEAD
//            try {
//                CloseStreams.closeRsPs(rs,ps);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//=======
//
//>>>>>>> origin/ike2
//        }
//        return null;
//    }
//
//    @Override
//    public boolean insertAccessRole(AccessRole accessRole) {
//        try {
//<<<<<<< HEAD
//            ps = getConnection().prepareStatement("INSERT INTO AccessRoles (accessRole) VALUES (?)");
//            ps.setString(1,accessRole.getRoleName());
//=======
//            ps = DBConnection.getConnection().prepareStatement("INSERT INTO AccessRoles (accessRole) VALUES (?)");
//            ps.setString(1, accessRole.getRoleName());
//>>>>>>> origin/ike2
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//<<<<<<< HEAD
//            try {
//                CloseStreams.closePreparedStatment(ps);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//=======
//
//>>>>>>> origin/ike2
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateAccessRole(AccessRole accessRole) {
//        try {
//<<<<<<< HEAD
//            ps = getConnection().prepareStatement("UPDATE AccessRoles SET accessRole = ? WHERE accessRoleID = ?");
//=======
//            ps = DBConnection.getConnection().prepareStatement("UPDATE AccessRoles SET accessRole = ? WHERE accessRoleID = ?");
//>>>>>>> origin/ike2
//            ps.setString(1, accessRole.getRoleName());
//            ps.setInt(2, accessRole.getAccessRoleID());
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//<<<<<<< HEAD
//            try {
//                CloseStreams.closePreparedStatment(ps);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//=======
//
//>>>>>>> origin/ike2
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteAccessRole(AccessRole accessRole) {
//        try {
//<<<<<<< HEAD
//            ps = getConnection().prepareStatement("DELETE FROM AccessRoles WHERE accessRoleID = ?");
//            ps.setInt(1,accessRole.getRoleId());
//=======
//            ps = DBConnection.getConnection().prepareStatement("DELETE FROM AccessRoles WHERE accessRoleID = ?");
//            ps.setInt(1, accessRole.getAccessRoleID());
//>>>>>>> origin/ike2
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//<<<<<<< HEAD
//        } finally{
//            try {
//                CloseStreams.closePreparedStatment(ps);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//=======
//        } finally {
//
//>>>>>>> origin/ike2
//        }
//        return false;
//    }
//
//    @Override
//    public List<AccessRole> getAccessRoles() {
//        List<AccessRole> accessRoles = new ArrayList<>();
//        try {
//<<<<<<< HEAD
//            s = getConnection().createStatement();
//=======
//            s = DBConnection.getConnection().createStatement();
//>>>>>>> origin/ike2
//            rs = s.executeQuery("SELECT * FROM AccessRoles");
//            while (rs.next()) {
//                AccessRole accessRole = extractAccessRoleFromResultSet(rs);
//                accessRoles.add(accessRole);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//<<<<<<< HEAD
//        } finally{
//            try {
//                if(s != null){
//                    s.close();
//                } else if(rs != null){
//                    rs.close();
//                }
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//=======
//        } finally {
//
//>>>>>>> origin/ike2
//        }
//        return accessRoles;
//    }
//
//    private AccessRole extractAccessRoleFromResultSet(ResultSet resultSet) throws SQLException {
//        int accessRoleId = resultSet.getInt("accessRoleID");
//        String accessRole = resultSet.getString("accessRole");
//        return new AccessRole(accessRoleId, accessRole);
//    }
//}
