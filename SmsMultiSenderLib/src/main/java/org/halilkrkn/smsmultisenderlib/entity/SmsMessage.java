package org.halilkrkn.smsmultisenderlib.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmsMessage {
    private String phoneNumber;
    private String messageBody;
}