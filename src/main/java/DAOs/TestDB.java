package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.ModuleDB;
import DBConnection.DBConnection;
import Models.Courses.Module;
import Models.QA.Question;
import Models.Tests.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDB extends DBConnection implements TestDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private ModuleDAO mdao = new ModuleDB();
    private QuestionDAO qdao = new QuestionDB();

    @Override
    public Test getTest(int id) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Tests WHERE testID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractTestFromResultSet(rs);
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
    public boolean insetTest(Test test) {
        int update = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Tests (testName, moduleID, startDate, duration, weight) VALUES (?,?,?,?,?)");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModule().getModuleID());
            ps.setLong(3, test.getStartDate());
            ps.setLong(4, test.getDuration());
            ps.setDouble(5, test.getWeight());
            update = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return update == 1;
    }

    @Override
    public boolean deleteTest(int testId) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Tests WHERE testID = ?");
            ps.setInt(1, testId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
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
            ps = getConnection().prepareStatement("UPDATE Tests SET testName = ?, moduleID = ?, startDate = ?, duration = ?, weight = ?  WHERE testID = ?");
            ps.setString(1, test.getTestName());
            ps.setInt(2, test.getModule().getModuleID());
            ps.setLong(3, test.getStartDate());
            ps.setLong(4, test.getDuration());
            ps.setDouble(5, test.getWeight());
            ps.setInt(6, test.getTestID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Tests");
            rs = ps.executeQuery();
            while (rs.next()) {
                tests.add(extractTestFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return tests;
    }

    @Override
    public List<Test> getAllTestsByModuleID(int moduleID) {
        List<Test> tests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Tests WHERE moduleID = ?");
            ps.setInt(1, moduleID);
            rs = ps.executeQuery();
            while (rs.next()) {
                tests.add(extractTestFromResultSet(rs));
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
        return tests;
    }

    private Test extractTestFromResultSet(ResultSet resultSet) throws SQLException {
        int testId = resultSet.getInt("testID");
        String testName = resultSet.getString("testName");
        int moduleId = resultSet.getInt("moduleID");
        long startDate = resultSet.getLong("startDate");
        long duration = resultSet.getLong("duration");
        double weight = resultSet.getLong("weight");
        return new Test(testId, testName, mdao.getModule(moduleId), startDate, duration, weight);
    }

    @Override
    public int getLastInsertID() {
        int id = 0;
        try {
            ps = getConnection().prepareStatement("SELECT max(testID) AS max FROM tests");
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("max");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    @Override
    public void insertTestQuestions(int id, int questionID) {
        try {
            ps = getConnection().prepareStatement("INSERT INTO test_questions (testID, questionID) VALUES (?,?)");
            ps.setInt(1, id);
            ps.setInt(2, questionID);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Question> getAllQuestionsInTest(int testId) {
        List<Question> questions = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM test_questions WHERE testID = ?");
            ps.setInt(1, testId);
            rs = ps.executeQuery();
            while(rs.next()){
                questions.add(qdao.getQuestion(rs.getInt("questionID")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return questions;
    }
}
