package org.halilkrkn.smsmultisenderlib.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(SmsSenderProperties.class)
public class SmsConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}