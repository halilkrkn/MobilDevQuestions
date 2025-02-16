package org.halilkrkn.smsmultisenderlib.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.halilkrkn.smsmultisenderlib.config.SmsSenderProperties;
import org.halilkrkn.smsmultisenderlib.entity.MessageBody;
import org.halilkrkn.smsmultisenderlib.entity.MessageItem;
import org.halilkrkn.smsmultisenderlib.entity.Messages;
import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsSenderServiceImpl implements SmsSenderService {
    private final SmsSenderProperties properties;
    private final RestTemplate restTemplate;

    @Override
    public void sendMultipleMessages(List<SmsMessage> messages) {
        try {

            MessageBody requestBody = createRequestBody(messages);
            String xmlRequest = convertToXml(requestBody);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            String response = restTemplate.postForObject(
                    properties.getApiUrl(),
                    xmlRequest,
                    String.class
            );

            log.info("SMS sent successfully. Response: {}", response);
        } catch (Exception e) {
            log.error("Error sending SMS: ", e);
            throw new RuntimeException("Failed to send SMS", e);
        }
    }

    private MessageBody createRequestBody(List<SmsMessage> messages) {
        MessageBody mainBody = new MessageBody();
        mainBody.setUserName(properties.getUsername());
        mainBody.setPassWord(properties.getPassword());
        mainBody.setOriginator(properties.getOriginator());

        Messages messageWrapper = new Messages();
        List<MessageItem> messageItems = messages.stream()
                .map(msg -> {
                    MessageItem item = new MessageItem();
                    item.setMessageBody(msg.getMessageBody());
                    item.setNumber(msg.getPhoneNumber());
                    return item;
                })
                .collect(Collectors.toList());

        messageWrapper.setMessageList(messageItems);
        mainBody.setMessages(messageWrapper);

        return mainBody;
    }

    private String convertToXml(MessageBody mainBody) throws Exception {
        JAXBContext context = JAXBContext.newInstance(MessageBody.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(mainBody, sw);
        return sw.toString();
    }

}
