package org.halilkrkn.smsmultisenderlib.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "mobildev.sms")
public class SmsProperties {
    private String url;
    private String username;
    private String password;
    private int accountId;
    private String originator;
    private int blacklist;
    private int encoding;
    private int action;
    private String messageType;
}