package com.triplethread.src.messaging;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class SenderImp {

    public static String sendSMS(LocalDateTime dateTime, String recieversCellNum, String msg) throws JAXBException, FileNotFoundException, IOException, ParserConfigurationException {
        String date = dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm:ss"));
        Client restClient;
        WebTarget webTarget;
        restClient = ClientBuilder.newClient();
        webTarget = restClient.target("http://l96.40.180.157:8080/sms/sms_request"); 
        Response response;
        String xmlPayload = "";
        String msgUser = "GROUP1";
        String password = "group1";
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element smsReq = doc.createElement("smsreq");
            doc.appendChild(smsReq);

            Element datetime = doc.createElement("datetime");
            datetime.appendChild(doc.createTextNode(date));
            smsReq.appendChild(datetime);

            Element user = doc.createElement("user");
            user.appendChild(doc.createTextNode(msgUser));
            smsReq.appendChild(user);

            Element pass = doc.createElement("pass");
            pass.appendChild(doc.createTextNode(password));
            smsReq.appendChild(pass);

            Element msisdn = doc.createElement("msisdn");
            msisdn.appendChild(doc.createTextNode(recieversCellNum));
            smsReq.appendChild(msisdn);

            Element message = doc.createElement("message");
            message.appendChild(doc.createTextNode(msg));
            smsReq.appendChild(message);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            xmlPayload = writer.getBuffer().toString();
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        response = webTarget.request().post(Entity.xml(xmlPayload));
        return response.readEntity(String.class);
    }
    
    public static void main(String[] args) {
        try {
            sendSMS(LocalDateTime.now(), "0656097973", "This is a test!!!");
        } catch (JAXBException | IOException | ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }
}
