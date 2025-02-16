package org.halilkrkn.smsmultisenderlib.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "sms")
public class SmsSenderProperties {
    private String username;
    private String password;
    private String originator;
    private String apiUrl = "https://xmlapi.mobildev.com";
}