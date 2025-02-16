package org.halilkrkn.smsmultisenderlib.entity;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Messages {
    @XmlElement(name = "Message")
    private List<MessageItem> messageList;
}