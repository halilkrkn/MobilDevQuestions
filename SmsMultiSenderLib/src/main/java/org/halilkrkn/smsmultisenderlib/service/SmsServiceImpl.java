package org.halilkrkn.smsmultisenderlib.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.halilkrkn.smsmultisenderlib.config.SmsProperties;
import org.halilkrkn.smsmultisenderlib.dto.SmsMultiSenderRequest;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessageContent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final SmsProperties smsProperties;
    private final WebClient webClient;

    @Override
    public String sendSms(List<SmsMessage> messages) {
        try {
            // Domain modelden XML modeline dönüştürme işlemi
            List<SmsMessageContent> messageContents = messages.stream().map(msg -> {
                SmsMessageContent content = new SmsMessageContent();
                content.setMesgbody(msg.getMessageBody());
                content.setNumber(msg.getNumber());
                return content;
            }).collect(Collectors.toList());

            // API isteği için XML nesnesinin oluşturulması
            SmsMultiSenderRequest request = new SmsMultiSenderRequest();
            request.setUserName(smsProperties.getUsername());
            request.setPassword(smsProperties.getPassword());
            request.setAction(smsProperties.getAction());
            request.setMessages(messageContents);
            request.setAccountId(smsProperties.getAccountId());
            request.setOriginator(smsProperties.getOriginator());
            request.setBlacklist(smsProperties.getBlacklist());
            request.setSDate(""); // İsteğe bağlı gönderim tarihi
            request.setEDate("");
            request.setEncoding(smsProperties.getEncoding());
            request.setMessageType(smsProperties.getMessageType());
            request.setRecipientType(""); // Opsiyonel

            // JAXB ile XML'e dönüştürme
            JAXBContext jaxbContext = JAXBContext.newInstance(SmsMultiSenderRequest.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            marshaller.marshal(request, sw);
            String xmlPayload = sw.toString();

            // WebClient ile POST isteği atma
            Mono<String> responseMono = webClient.post()
                    .uri(smsProperties.getUrl())
                    .header("Content-Type", "application/xml")
                    .bodyValue(xmlPayload)
                    .retrieve()
                    .bodyToMono(String.class);

            // Basitlik açısından block() kullanıldı. (Reaktif yapıyı tercih ediyorsanız asenkron yaklaşım uygulanabilir.)
            return responseMono.block();
        } catch (Exception e) {
            log.error("SMS gönderimi sırasında hata meydana geldi", e);
            throw new RuntimeException("SMS gönderimi sırasında hata meydana geldi", e);
        }

    }
}