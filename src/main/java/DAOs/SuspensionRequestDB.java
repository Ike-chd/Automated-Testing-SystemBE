package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import DBConnection.DBConnection;
import Models.Communication.SuspensionRequest;
import Models.Users.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;

public class SuspensionRequestDB extends DBConnection implements SuspensionRequestDAO {
   

    private PreparedStatement ps;
    private ResultSet rs;
    private CourseDAO cdao;

//    @Override
//    public SuspensionRequest getSuspensionRequest(int ssId) {
//        try {
//            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE SSID = ?");
//            ps.setInt(1, ssId);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return extractSuspensionRequestFromResultSet(rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                CloseStreams.closeRsPs(rs,ps);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
//        try {
//            ps = getConnection().prepareStatement("INSERT INTO SuspensionRequests (studentID, requestID, duration, confirmID, reason, active, dateInitiated) VALUES (?,?,?,?,?,?,?)");
//            ps.setInt(1, ssRequest.getStudent().getUserID());
//            ps.setInt(2, ssRequest.getRequestedBy().getUserID());
//            ps.setInt(3, ssRequest.getDuration());
//            ps.setInt(4, ssRequest.getConfirmedBy().getUserID());
//            ps.setBoolean(5, ssRequest.isActive());
//            ps.setTimestamp(6, Timestamp.valueOf(ssRequest.));
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
//        try {
//            ps = getConnection().prepareStatement("UPDATE SuspensionRequests SET studentID = ?, requestID = ?, duration = ?, confirmID = ?, reason = ?, active = ?, dateInitiated = ? WHERE SSID = ?");
//            ps.setInt(1, ssRequest.getRequestId());
//            ps.setInt(2, ssRequest.getDuration());
//            ps.setInt(3, ssRequest.getConfirmId());
//            ps.setString(4, ssRequest.getReason());
//            ps.setBoolean(5, ssRequest.isActive());
//            ps.setTimestamp(6, Timestamp.valueOf(ssRequest.getDateInitiated()));
//            ps.setInt(7, ssRequest.getSsId());
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteSuspensionRequest(SuspensionRequest ssRequest) {
//        try {
//            ps = getConnection().prepareStatement("DELETE FROM SuspensionRequests WHERE SSID = ?");
//            ps.setInt(1, ssRequest.getSsId());
//            int affectedRows = ps.executeUpdate();
//            return affectedRows > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public List <SuspensionRequest> getAllSuspensionRequests() {
//        List<SuspensionRequest> suspensionRequests = new ArrayList<>();
//        try {
//            statement = DBConnection.getConnection().createStatement();
//            rs = statement.executeQuery("SELECT * FROM SuspensionRequests");
//            while (rs.next()) {
//                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
//                suspensionRequests.add(suspensionRequest);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return suspensionRequests;
//    }
//
//    @Override
//    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
//        List<SuspensionRequest> activeSuspensionRequests = new ArrayList<>();
//        try {
//            statement = DBConnection.getConnection().createStatement();
//            rs = statement.executeQuery("SELECT * FROM SuspensionRequests WHERE active = true");
//            while (rs.next()) {
//                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
//                activeSuspensionRequests.add(suspensionRequest);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return activeSuspensionRequests;
//    }
//
//    @Override
//    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
//        List<SuspensionRequest> studentSuspensionRequest = new ArrayList<>();
//        try {
//            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE studentID = ?");
//            ps.setString(1, student.getUsername());
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
//                studentSuspensionRequest.add(suspensionRequest);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return studentSuspensionRequest;
//    }
//
//    private SuspensionRequest extractSuspensionRequestFromResultSet(ResultSet resultSet) throws SQLException {
//        int ssId = resultSet.getInt("SSID");
//        int studentId = resultSet.getInt("studentID");
//        int requestId = resultSet.getInt("requestID");
//        int duration = resultSet.getInt("duration");
//        int confirmId = resultSet.getInt("confirmId");
//        String reason = resultSet.getString("reason");
//        boolean active = resultSet.getBoolean("active");
//        Timestamp dateInitiated = resultSet.getTimestamp("dateInitiated");
//        Student student = getStudentById(studentId);
//        //return new SuspensionRequest(ssId, student, requestId, duration, confirmId, reason, active, dateInitiated.toLocalDateTime());
//        return null;
//    }
//
//    private Student getStudentById(int studentId) throws SQLException {
//        ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Students WHERE studentID = ?");
//        ps.setInt(1, studentId);
//        rs = ps.executeQuery();
//        if (rs.next()) {
//            return extractStudentFromResultSet(rs);
//        }
//        return null;
//    }
//
//    private Student extractStudentFromResultSet(ResultSet resultSet) throws SQLException {
//        int studentId = resultSet.getInt("studentID");
//        String firstname = resultSet.getString("firstname");
//        String username = "";
//        String surname = resultSet.getString("surname");
//        String email = resultSet.getString("email");
//        String address = resultSet.getString("address");
//        String idNumber = resultSet.getString("idNumber");
//        int courseID = resultSet.getInt("courseID");
//        String password = resultSet.getString("password");
//        cdao = new CourseDB();
////        return new Student(studentId, username, firstname, surname, email, idNumber, password, studentId, cdao.getCourse(courseID));
//        return null;
//    }

    @Override
    public SuspensionRequest getSuspensionRequest(int ssId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteSuspensionRequest(SuspensionRequest ssRequest) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SuspensionRequest> getAllSuspensionRequests() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
