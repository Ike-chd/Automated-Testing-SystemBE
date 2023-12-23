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

public class ModuleDB extends DBConnection implements ModuleDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Module getModule(int moduleId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?");
            ps.setInt(1, moduleId);
            rs = ps.executeQuery();
            if (rs.next()) {
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
            ps.setInt(3, module.getModuleID());
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
                CloseStreams.closeRsPs(rs, ps);
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
        return new Module(moduleID, moduleName, moduleDescription);
    }

    @Override
    public int getModuleIdByName(String moduleName) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleName = ?");
            ps.setString(1, moduleName);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractModuleFromResultSet(rs).getModuleID();
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
        return 0;
    }

    @Override
    public boolean addPrereqModule(int module1, int module2) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO prerequM(mid,pmid) VALUES (?,?)");
            ps.setInt(1, module1);
            ps.setInt(2, module2);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public int getLastPrimaryKey() {
        try {
            ps = getConnection().prepareStatement("SELECT MAX(moduleID) AS MAX FROM Modules");
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("MAX");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Module> getAllPrereqModulesByModuleID(int moduleId) {
        List<Module> modules = new ArrayList<Module>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM prerequM WHERE mid = ?");
            ps.setInt(1, moduleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int preMID = rs.getInt("pmid");
                try (PreparedStatement ps2 = getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?")) {
                    ps2.setInt(1, preMID);
                    try (ResultSet rs2 = ps2.executeQuery()) {
                        while (rs2.next()) {
                            modules.add(extractModuleFromResultSet(rs2));
                        }
                    }
                }
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
        return modules;
    }
    
    @Override
    public List<Module> getAllModulesInACourse(int courseId){
        List<Module> modules = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM course_module WHERE course_id = ?");
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int mID = rs.getInt("module_id");
                try (PreparedStatement ps2 = getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?")) {
                    ps2.setInt(1, mID);
                    try (ResultSet rs2 = ps2.executeQuery()) {
                        while (rs2.next()) {
                            modules.add(extractModuleFromResultSet(rs2));
                        }
                    }
                }
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
        return modules;
    }
    
    @Override
    public boolean insertModuleIntoCourse(int courseId, int moduleId){
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO course_module (course_id, module_id) VALUES (?,?)");
            ps.setInt(1, courseId);
            ps.setInt(2, moduleId);
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
}
