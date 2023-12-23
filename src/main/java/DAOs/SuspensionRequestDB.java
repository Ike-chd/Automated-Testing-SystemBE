package DAOs;

import DAOs.CloseStreams.CloseStreams;
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
import java.util.ArrayList;
import java.util.List;

public class SuspensionRequestDB extends DBConnection implements SuspensionRequestDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private StudentDAO sdao = new StudentDB();
    private FacultyMemberDAO fdao = new FacultyMemberDB();
    private AdminDAO adao = new AdminDB();

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
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO SuspensionRequests (studentID, requestID, duration, reason, active, dateInitiated) VALUES (?,?,?,?,?,?)");
            ps.setInt(1, ssRequest.getStudent().getUserID());
            ps.setInt(2, ssRequest.getRequestedBy().getUserID());
            ps.setInt(3, ssRequest.getDuration());
            ps.setString(4, ssRequest.getReason());
            ps.setBoolean(5, ssRequest.isActive());
            ps.setLong(6, ssRequest.getStart());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE SuspensionRequests SET studentID = ?, requestID = ?, duration = ?, confirmID = ?, reason = ?, active = ?, dateInitiated = ? WHERE SSID = ?");
            ps.setInt(1, ssRequest.getStudent().getUserID());
            ps.setInt(2, ssRequest.getRequestedBy().getUserID());
            ps.setInt(3, ssRequest.getDuration());
            ps.setInt(4, ssRequest.getConfirmedBy().getUserID());
            ps.setBoolean(5, ssRequest.isActive());
            ps.setLong(6, ssRequest.getStart());
            ps.setInt(7, ssRequest.getSsId());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean deleteSuspensionRequest(int ssId) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM SuspensionRequests WHERE SSID = ?");
            ps.setInt(1, ssId);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public List<SuspensionRequest> getAllSuspensionRequests() {
        List<SuspensionRequest> suspensionRequests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests");
            rs = ps.executeQuery();
            while (rs.next()) {
                suspensionRequests.add(extractSuspensionRequestFromResultSet(rs));
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
        return suspensionRequests;
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        List<SuspensionRequest> activeSuspensionRequests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE active = ?");
            ps.setBoolean(1, true);
            rs = ps.executeQuery();
            while (rs.next()) {
                activeSuspensionRequests.add(extractSuspensionRequestFromResultSet(rs));
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
                studentSuspensionRequest.add(extractSuspensionRequestFromResultSet(rs));
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
        long start= resultSet.getLong("dateInitiated");

        Student student = sdao.getStudent(studentId);
        FacultyMember requestedBy = fdao.getFacultyMember(requestId);
        Admin confirmedBy = adao.getAdmin(confirmId);

        return new SuspensionRequest(ssId, student, requestedBy, confirmedBy, start, duration, active, reason);
    }

    @Override
    public List<SuspensionRequest> getAllUnApprovedSReq() {
        List<SuspensionRequest> suspensionRequests = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE active = ?");
            ps.setBoolean(1, false);
            rs = ps.executeQuery();
            while(rs.next()){
                suspensionRequests.add(extractSuspensionRequestFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return suspensionRequests;
    }
}
