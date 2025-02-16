package org.halilkrkn.smsmultisenderlib.controller;

import lombok.RequiredArgsConstructor;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;
import org.halilkrkn.smsmultisenderlib.service.SmsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<String> sendSms(@RequestBody List<SmsMessage> messages) {
        String response = smsService.sendSms(messages);
        return ResponseEntity.ok(response);
    }

    //swagger link
    //http://localhost:8080/swagger-ui.html
}
