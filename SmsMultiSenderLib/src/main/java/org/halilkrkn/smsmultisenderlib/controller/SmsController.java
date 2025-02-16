package org.halilkrkn.smsmultisenderlib.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.halilkrkn.smsmultisenderlib.dto.SmsRequest;
import org.halilkrkn.smsmultisenderlib.dto.SmsResponse;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;
import org.halilkrkn.smsmultisenderlib.service.SmsSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {
    
    private final SmsSenderService smsSenderService;

    @PostMapping("/send")
    public ResponseEntity<SmsResponse> sendSms(
            @Valid @RequestBody SmsRequest request,
            @RequestHeader(value = "X-Correlation-ID", required = false) String correlationId
    ) {
        
        String requestId = correlationId != null ? correlationId : UUID.randomUUID().toString();
        log.info("Received SMS send request with ID: {}", requestId);

        try {


            List<SmsMessage> messages = request.getMessages().stream()
                    .map(dto -> SmsMessage.builder()
                            .phoneNumber(dto.getPhoneNumber())
                            .messageBody(dto.getMessageBody())
                            .build())
                    .collect(Collectors.toList());

            smsSenderService.sendMultipleMessages(messages);

            SmsResponse response = SmsResponse.builder()
                    .status("SUCCESS")
                    .message("Messages sent successfully")
                    .timestamp(LocalDateTime.now())
                    .requestId(requestId)
                    .build();

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error while sending SMS with request ID {}: {}", requestId, e.getMessage());
            
            SmsResponse errorResponse = SmsResponse.builder()
                    .status("ERROR")
                    .message("Failed to send messages: " + e.getMessage())
                    .timestamp(LocalDateTime.now())
                    .requestId(requestId)
                    .build();

            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }

//    @GetMapping("/status/{requestId}")
//    public ResponseEntity<SmsResponse> getStatus(@PathVariable String requestId) {
//        // Bu metod, SMS gönderim durumunu kontrol etmek için kullanılabilir
//        // Şu an için basit bir yanıt dönüyoruz
//        SmsResponse response = SmsResponse.builder()
//                .status("DELIVERED")
//                .message("Messages were delivered successfully")
//                .timestamp(LocalDateTime.now())
//                .requestId(requestId)
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
}