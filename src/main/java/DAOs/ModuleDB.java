package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DBConnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Models.Courses.Module;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            ps = getConnection().prepareStatement("INSERT INTO Modules (moduleName, moduleDescription) VALUES (?,?)");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
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
    public boolean deleteModule(Module module) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Modules WHERE moduleID = ?");
            ps.setInt(1, module.getModuleID());
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
    public boolean updateModule(Module module) {
        try {
            ps = getConnection().prepareStatement("UPDATE Modules SET moduleName = ?, moduleDescription = ? WHERE moduleID = ?");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
            ps.setInt(3,module.getModuleID());
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
    public List<Module> allModules() {
        List<Module> modules = new ArrayList<>();
        //TODO
        return null;
    }
    private Module extractModuleFromResultSet(ResultSet resultSet){
        return null;
    }
}
