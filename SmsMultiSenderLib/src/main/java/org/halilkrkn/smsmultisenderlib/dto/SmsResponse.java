package org.halilkrkn.smsmultisenderlib.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class SmsResponse {
    private String status;
    private String message;
    private LocalDateTime timestamp;
    private String requestId;
}