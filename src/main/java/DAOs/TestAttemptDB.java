package DAOs;

import DAOs.DAOControllers.Tests.TestAttemptDAO;
import DBConnection.DBConnection;
import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestAttemptDB extends DBConnection implements TestAttemptDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    @Override
    public TestAttempt getTestAttempt(int id) {
        try {
            ps = getConnection().prepareStatement("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TestAttempt> getAllTestAttemptsByTest(Test test) {
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
}
