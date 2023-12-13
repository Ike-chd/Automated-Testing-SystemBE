package Services;

import Models.Communication.SMS;
import Services.ServicesInterfaces.SMSService;
import java.util.Timer;
import java.util.TimerTask;

public class SMSHandler implements SMSService {

    @Override
    public void createSMSTimer(SMS sms) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                sendSMS();
            }
        };
        timer.schedule(task, sms.getDate().getTime());
    }

    @Override
    public boolean sendSMS() {
        return true;
    }
}
