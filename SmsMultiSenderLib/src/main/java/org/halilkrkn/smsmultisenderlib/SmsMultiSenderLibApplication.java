package org.halilkrkn.smsmultisenderlib;

import org.halilkrkn.smsmultisenderlib.config.SmsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SmsProperties.class)
public class SmsMultiSenderLibApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsMultiSenderLibApplication.class, args);
	}

}
