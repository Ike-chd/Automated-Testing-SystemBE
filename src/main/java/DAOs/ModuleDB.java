package DAOs;

import DAOs.DAOControllers.Courses.ModuleDAO;
import DBConnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import Models.Courses.Module;
import java.util.ArrayList;

public class ModuleDB implements ModuleDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Module module;

    @Override
    public Module getModule(int moduleId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?");
            ps.setInt(1, moduleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractModuleFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertModule(int moduleId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO Modules (moduleName, moduleDescription) VALUES (?,?)");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteModule(int moduleId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("DELETE FROM Modules WHERE moduleID = ?");
            ps.setInt(1, module.getModuleID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateModule(int moduleId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("UPDATE Modules SET moduleName = ?, moduleDescription = ? WHERE moduleID = ?");
            ps.setString(1, module.getModuleName());
            ps.setString(2, module.getModuleDescription());
            ps.setInt(3, module.getModuleID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Module> allModules() {
        List<Module> modules = new ArrayList<>();
        //TODO
        return null;
    }

    private Module extractModuleFromResultSet(ResultSet resultSet) {
        return null;
    }
}
