package org.halilkrkn.smsmultisenderlib.entity;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageItem {
    @XmlElement(name = "Mesgbody")
    private String messageBody;
    
    @XmlElement(name = "Number")
    private String number;
}