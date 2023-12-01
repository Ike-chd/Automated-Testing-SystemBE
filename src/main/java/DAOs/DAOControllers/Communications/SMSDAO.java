package DAOs.DAOControllers.Communications;

import Models.Communication.SMS;
import java.util.List;

public interface SMSDAO {
    public SMS getSMS(int id);
    public List<SMS> getAllSMS();
    public boolean addSMS(SMS sms);
    public boolean deleteSMS(int id);
    public boolean updateSMS(SMS sms);
}