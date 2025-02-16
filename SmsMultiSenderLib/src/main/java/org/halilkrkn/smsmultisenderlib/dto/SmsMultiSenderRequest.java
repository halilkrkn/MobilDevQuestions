package org.halilkrkn.smsmultisenderlib.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessageContent;

import java.util.List;

@Data
@XmlRootElement(name = "MainmsgBody")
@XmlAccessorType(XmlAccessType.FIELD)
public class SmsMultiSenderRequest {

    @XmlElement(name = "UserName")
    private String userName;

    @XmlElement(name = "PassWord")
    private String password;

    @XmlElement(name = "Action")
    private int action;

    @XmlElementWrapper(name = "Messages")
    @XmlElement(name = "Message")
    private List<SmsMessageContent> messages;

    @XmlElement(name = "AccountId")
    private int accountId;

    @XmlElement(name = "Originator")
    private String originator;

    @XmlElement(name = "Blacklist")
    private int blacklist;

    @XmlElement(name = "SDate")
    private String sDate;

    @XmlElement(name = "EDate")
    private String eDate;

    @XmlElement(name = "Encoding")
    private int encoding;

    @XmlElement(name = "MessageType")
    private String messageType;

    @XmlElement(name = "RecipientType")
    private String recipientType;
}