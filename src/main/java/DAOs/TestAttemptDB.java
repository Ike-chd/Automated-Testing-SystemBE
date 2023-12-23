package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestAttemptDB extends DBConnection implements TestAttemptDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private TestDAO tdao = new TestDB();
    private StudentDAO sdao = new StudentDB();
    @Override
    public TestAttempt getTestAttempt(int id) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Attempt WHERE attemptId = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                return extractTestAttemptFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<TestAttempt> getAllTestAttemptsByTest(Test test) {
        List<TestAttempt> attempts = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Attempt WHERE testID_attempt = ?");
            ps.setInt(1,test.getTestID());
            rs = ps.executeQuery();
            while(rs.next()) {
                TestAttempt attempt = extractTestAttemptFromResultSet(rs);
                attempts.add(attempt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attempts;
    }

    @Override
    public List<TestAttempt> getAllTestAttemptsByStudent(Student student) {
        List<TestAttempt> attempts = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Attempt WHERE studentID_attemp = ?");
            ps.setInt(1,student.getUserID());
            rs = ps.executeQuery();
            while(rs.next()){
                attempts.add(extractTestAttemptFromResultSet(rs));
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
        return attempts;
    }

    @Override
    public List<TestAttempt> getAllTestAttempts() {
        List<TestAttempt> attempts = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Attempt");
            rs = ps.executeQuery();
            while(rs.next()) {
                attempts.add(extractTestAttemptFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return attempts;
    }

    @Override
    public List<TestAttempt> getAllTestAttemptsByTestAndStudent(Test test, Student student) {
        List<TestAttempt> attempts = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Attempt WHERE studentID_attemp = ? AND testID_attempt = ?");
            ps.setInt(1,student.getUserID());
            ps.setInt(2,test.getTestID());
            rs = ps.executeQuery();
            while (rs.next()) {
                attempts.add(extractTestAttemptFromResultSet(rs));
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
        return attempts;
    }

    private TestAttempt extractTestAttemptFromResultSet(ResultSet resultSet) throws SQLException {
        int testAttemptID = resultSet.getInt("attemptId");
        int testID_attempt = resultSet.getInt("testID_attempt");
        int studentID_attemp = resultSet.getInt("studentID_attemp");
        double rating = resultSet.getDouble("Rating");
        return new TestAttempt(testAttemptID,tdao.getTest(testID_attempt),sdao.getStudent(studentID_attemp),rating);
    }

    @Override
    public boolean insertTestAttempt(TestAttempt testAttempt) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Test_Attempt (studentID_attemp, testID_attempt, Rating, StartDateTime, EndDateTime, marks) VALUES(?,?,?,?,?,?)");
            ps.setInt(1, testAttempt.getStudent().getUserID());
            ps.setInt(2, testAttempt.getTest().getTestID());
            ps.setDouble(3, testAttempt.getRating());
            ps.setLong(4, testAttempt.getStartTime());
            ps.setLong(5, testAttempt.getEndTime());
            ps.setInt(6, testAttempt.getTotalMarks());
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
