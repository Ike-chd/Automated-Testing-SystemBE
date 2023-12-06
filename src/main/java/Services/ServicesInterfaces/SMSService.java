package Services.ServicesInterfaces;

import Models.Communication.SMS;

public interface SMSService {

    public void createSMSTimer(SMS sms);

    public boolean sendSMS();
}
