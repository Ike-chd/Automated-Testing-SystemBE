package DAOs;

import Models.Tests.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDB {

    private static final String URL = "jdbc:mysql://localhost:3306/student_enrolment_system";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Test getTestByID(int testID) {
        String query = "SELECT * FROM tests WHERE testID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Test test = new Test();
                test.setTestID(resultSet.getInt("testID"));
                test.setTestName(resultSet.getString("testName"));
                return test;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        String query = "SELECT * FROM tests";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Test test = new Test();
                test.setTestID(resultSet.getInt("testID"));
                test.setTestName(resultSet.getString("testName"));
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tests;
    }

    public static void createTest(Test test) {
        String query = "INSERT INTO tests (testName) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, test.getTestName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                test.setTestID(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTest(Test test) {
        String query = "UPDATE tests SET testName = ? WHERE testID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, test.getTestName());
            preparedStatement.setLong(2, test.getTestID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTest(int testID) {
        String query = "DELETE FROM tests WHERE testID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, testID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }
}
