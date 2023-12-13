package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import DAOs.DAOControllers.Users.AdminDAO;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Communication.SuspensionRequest;
import Models.Users.Admin;
import Models.Users.FacultyMember;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuspensionRequestDB extends DBConnection implements SuspensionRequestDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private StudentDAO sdao;
    private FacultyMemberDAO fdao;
    private AdminDAO adao;

    @Override
    public SuspensionRequest getSuspensionRequest(int ssId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE SSID = ?");
            ps.setInt(1, ssId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractSuspensionRequestFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        try {
            ps = getConnection().prepareStatement("INSERT INTO SuspensionRequests (studentID, requestID, duration, confirmID, reason, active, dateInitiated) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, ssRequest.getStudent().getUserID());
            ps.setInt(2, ssRequest.getRequestedBy().getUserID());
            ps.setInt(3, ssRequest.getDuration());
            ps.setInt(4, ssRequest.getConfirmedBy().getUserID());
            ps.setBoolean(5, ssRequest.isActive());
            ps.setTimestamp(6, ssRequest.getDateInitiated());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        try {
            ps = getConnection().prepareStatement("UPDATE SuspensionRequests SET studentID = ?, requestID = ?, duration = ?, confirmID = ?, reason = ?, active = ?, dateInitiated = ? WHERE SSID = ?");
            ps.setInt(1, ssRequest.getStudent().getUserID());
            ps.setInt(2, ssRequest.getRequestedBy().getUserID());
            ps.setInt(3, ssRequest.getDuration());
            ps.setInt(4, ssRequest.getConfirmedBy().getUserID());
            ps.setBoolean(5, ssRequest.isActive());
            ps.setTimestamp(6, ssRequest.getDateInitiated());
            ps.setInt(7, ssRequest.getSsId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean deleteSuspensionRequest(int ssId) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM SuspensionRequests WHERE SSID = ?");
            ps.setInt(1, ssId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public List<SuspensionRequest> getAllSuspensionRequests() {
        List<SuspensionRequest> suspensionRequests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests");
            rs = ps.executeQuery();
            while (rs.next()) {
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                suspensionRequests.add(suspensionRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return suspensionRequests;
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        List<SuspensionRequest> activeSuspensionRequests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE active = true");
            rs = ps.executeQuery();
            while (rs.next()) {
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                activeSuspensionRequests.add(suspensionRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return activeSuspensionRequests;
    }

    @Override
    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
        List<SuspensionRequest> studentSuspensionRequest = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE studentID = ?");
            ps.setInt(1, student.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                studentSuspensionRequest.add(suspensionRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                Logger.getLogger(SuspensionRequestDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return studentSuspensionRequest;
    }

    private SuspensionRequest extractSuspensionRequestFromResultSet(ResultSet resultSet) throws SQLException {
        int ssId = resultSet.getInt("SSID");
        int studentId = resultSet.getInt("studentID");
        int requestId = resultSet.getInt("requestID");
        int duration = resultSet.getInt("duration");
        int confirmId = resultSet.getInt("confirmID");
        String reason = resultSet.getString("reason");
        boolean active = resultSet.getBoolean("active");
        Timestamp dateInitiated = resultSet.getTimestamp("dateInitiated");

        Student student = sdao.getStudent(studentId);
        FacultyMember requestedBy = fdao.getFacultyMember(requestId);
        Admin confirmedBy = adao.getAdmin(confirmId);

        return new SuspensionRequest(ssId, student, requestedBy, confirmedBy, dateInitiated, duration, active, reason);
    }
}
