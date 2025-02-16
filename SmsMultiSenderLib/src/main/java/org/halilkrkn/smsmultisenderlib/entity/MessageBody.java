package org.halilkrkn.smsmultisenderlib.entity;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "MainmsgBody")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageBody {
    @XmlElement(name = "UserName")
    private String userName;
    
    @XmlElement(name = "PassWord")
    private String passWord;
    
    @XmlElement(name = "Action")
    private int action = 1;
    
    @XmlElement(name = "Messages")
    private Messages messages;
    
    @XmlElement(name = "AccountId")
    private int accountId = 1;
    
    @XmlElement(name = "Originator")
    private String originator;
    
    @XmlElement(name = "Blacklist")
    private int blacklist = 1;
    
    @XmlElement(name = "SDate")
    private String sDate = "";
    
    @XmlElement(name = "EDate")
    private String eDate = "";
    
    @XmlElement(name = "Encoding")
    private int encoding = 1;
    
    @XmlElement(name = "MessageType")
    private String messageType = "N";
    
    @XmlElement(name = "RecipientType")
    private String recipientType = "";
}
