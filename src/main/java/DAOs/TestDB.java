
package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.ModuleDB;
import DBConnection.DBConnection;
import Models.Courses.Module;
import Models.Tests.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TestDB extends DBConnection implements TestDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    private DBConnection connection;
    private Statement s;
    private ModuleDAO mdao = new ModuleDB();
    
    @Override
    public Test getTest(int id) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Tests WHERE testID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractTestFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insetTest(Test test) {
        int update =0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Tests (testName, moduleID) VALUES (?,?)");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModuleID());
            update = ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }return update == 1;
    }

    @Override
    public boolean deleteTest(Test test) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Tests WHERE testID = ?");
            ps.setInt(1, test.getTestID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateTest(Test test) {
        try {
            ps = getConnection().prepareStatement("UPDATE Tests SET testName = ?, moduleID = ? WHERE testID = ?");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModuleID());
            ps.setInt(3,test.getTestID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Test> allModuleTests(Module module) {
        List <Test> tests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Tests WHERE moduleID = ?");
            ps.setInt(1, module.getModuleID());
            rs = ps.executeQuery();
            while(rs.next()){
                Test test = extractTestFromResultSet(rs);
                tests.add(test);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return tests;
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            s = getConnection().createStatement();
            rs = s.executeQuery("SELECT * FROM Tests");
            while(rs.next()){
                Test test = extractTestFromResultSet(rs);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
                try {
                    if (s!=null) {
                    s.close();
                    } else if(rs != null){
                        rs.close();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
        }
        return tests;
    }

    private Test extractTestFromResultSet(ResultSet resultSet) throws SQLException {
        int testId = resultSet.getInt("testID");
        String testName = resultSet.getString("testName");
        int moduleId = resultSet.getInt("moduleID");
        //return new Test(testId,testName,mdao.getModule(moduleId).getModuleID());
        return null;
    }
}
