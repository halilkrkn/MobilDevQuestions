package org.halilkrkn.smsmultisenderlib.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@Data
public class SmsRequest {
    @NotEmpty(message = "Messages cannot be empty")
    private List<MessageDto> messages;

}