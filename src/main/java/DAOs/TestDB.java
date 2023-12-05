package DAOs;

import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DBConnection.DBConnection;
import Models.Courses.Module;
import Models.Tests.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDB implements TestDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Statement s;
    private ModuleDAO mdao = new ModuleDB();

    @Override
    public Test getTest(int id) {
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Tests WHERE testID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractTestFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insetTest(Test test) {
        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO Tests (testName, moduleID) VALUES (?,?)");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModuleID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTest(Test test) {
        try {
            ps = DBConnection.getConnection().prepareStatement("DELETE FROM Tests WHERE testID = ?");
            ps.setInt(1, test.getTestID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTest(Test test) {
        try {
            ps = DBConnection.getConnection().prepareStatement("UPDATE Tests SET testName = ?, moduleID = ? WHERE testID = ?");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModuleID());
            ps.setInt(3, test.getTestID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Test> allModuleTests(Module module) {
        List<Test> tests = new ArrayList<>();
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Tests WHERE moduleID = ?");
            ps.setInt(1, module.getModuleID());
            rs = ps.executeQuery();
            while (rs.next()) {
                Test test = extractTestFromResultSet(rs);
                tests.add(test);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tests;
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            s = DBConnection.getConnection().createStatement();
            rs = s.executeQuery("SELECT * FROM Tests");
            while (rs.next()) {
                Test test = extractTestFromResultSet(rs);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    private Test extractTestFromResultSet(ResultSet resultSet) throws SQLException {
        int testId = resultSet.getInt("testID");
        String testName = resultSet.getString("testName");
        int moduleId = resultSet.getInt("moduleID");
        return new Test(testId, testName, mdao.getModule(moduleId).getModuleID());
    }

    private Module getModuleById(int moduleId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Modules WHERE moduleID = ?");
            ps.setInt(1, moduleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractModuleFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Module extractModuleFromResultSet(ResultSet resultSet) throws SQLException {
        int moduleId = resultSet.getInt("moduleID");
        String moduleName = resultSet.getString("moduleName");
        String moduleDescription = resultSet.getString("moduleDescription");
        return new Module(moduleId, moduleName, moduleDescription);
    }
}
