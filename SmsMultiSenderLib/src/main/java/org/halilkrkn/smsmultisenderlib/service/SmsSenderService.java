package org.halilkrkn.smsmultisenderlib.service;


import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;

import java.util.List;

public interface SmsSenderService {
    void sendMultipleMessages(List<SmsMessage> messages);
}
