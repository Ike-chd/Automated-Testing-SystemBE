package Services;

import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import DAOs.SuspensionRequestDB;
import Models.Communication.SuspensionRequest;
import Models.Users.Student;
import Services.ServicesInterfaces.SuspensionRequestService;
import java.util.Calendar;
import java.util.Date;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SuspensionRequestHandler implements SuspensionRequestService {

    private SuspensionRequestDAO srdao = new SuspensionRequestDB();

    @Override
    public SuspensionRequest getSuspensionRequest(int ssId) {
        return srdao.getSuspensionRequest(ssId);
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        return srdao.insertSuspensionRequest(ssRequest);
    }

    @Override
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        return srdao.updateSuspensionRequest(ssRequest);
    }
    
    @Override
    public boolean confirmSuspensionRequest(int ssID, int conID, int con){
        boolean susConfirmed = srdao.confirmSuspensionRequest(ssID, conID);
        if(susConfirmed && con==1){
            initiateSuspension(ssID);
        } else {
            if(susConfirmed){
                srdao.updateActiveStatus(ssID, false);
            }
        }
        return susConfirmed;
    }
    
    public void initiateSuspension(int ssid){
        SuspensionRequest sus = srdao.getSuspensionRequest(ssid);
        Date startDate = new Date();
        startDate.setTime(sus.getStart());
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                srdao.updateActiveStatus(ssid, true);
                Timer timer2 = new Timer();
                TimerTask task2 = new TimerTask(){
                    @Override
                    public void run(){
                        srdao.updateActiveStatus(ssid, false);
                    }
                };
                timer2.schedule(task2, sus.getDuration());
            }
        };
        timer.schedule(task, startDate);
    }

    @Override
    public boolean deleteSuspensionRequest(int ssId) {
        return srdao.deleteSuspensionRequest(ssId);
    }

    @Override
    public List<SuspensionRequest> getAllSuspensionRequests() {
        return srdao.getAllSuspensionRequests();
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        return srdao.getAllActiveSuspensionRequests();
    }

    @Override
    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
        return srdao.getSuspensionRequestsByStudent(student);
    }

    @Override
    public List<SuspensionRequest> getAllUnApproved() {
        return srdao.getAllUnApprovedSReq();
    }
}
