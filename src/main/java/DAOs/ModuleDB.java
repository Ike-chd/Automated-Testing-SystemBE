package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Models.Courses.Module;
import java.util.ArrayList;

public class ModuleDB extends DBConnection implements ModuleDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    @Override
    public Module getModule(int moduleId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?");
            ps.setInt(1, moduleId);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractModuleFromResultSet(rs);
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
    public boolean insertModule(Module module) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Modules (moduleName, moduleDescription) VALUES (?,?)");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows == 1;
    }
    @Override
    public boolean deleteModule(int module) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Modules WHERE moduleID = ?");
            ps.setInt(1, module);
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows == 1;
    }
    @Override
    public boolean updateModule(Module module) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Modules SET moduleName = ?, moduleDescription = ? WHERE moduleID = ?");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
            ps.setInt(3,module.getModuleID());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows == 1;
    }
    @Override
    public List<Module> allModules() {
        List<Module> modules = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Modules");
            rs = ps.executeQuery();
            while (rs.next()) {
                modules.add(extractModuleFromResultSet(rs));
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
        return modules;
    }
    private Module extractModuleFromResultSet(ResultSet resultSet) throws SQLException {
        int moduleID = resultSet.getInt("moduleID");
        String moduleName = resultSet.getString("moduleName");
        String moduleDescription = resultSet.getString("moduleDescription");
        return new Module(moduleID,moduleName,moduleDescription);
    }
}
