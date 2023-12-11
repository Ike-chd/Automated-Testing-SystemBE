package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Tests.TestAttemptDAO;
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
        return null;
    }

    @Override
    public List<TestAttempt> getAllTestAttemptsByStudent(Student student) {
        return null;
    }

    @Override
    public List<TestAttempt> getAllTestAttempts() {
        return null;
    }
    private TestAttempt extractTestAttemptFromResultSet(ResultSet resultSet){
        
    }
}
