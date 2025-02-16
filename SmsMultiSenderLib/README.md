# MobilDev SMS Sender Library

Java/Spring Boot kütüphanesi, MobilDev SMS API'sini kullanarak SMS gönderimi yapmanızı sağlar. Bu kütüphane Spring Boot 3.x ve Java 17 üzerinde çalışır.

## Özellikler

- Toplu SMS gönderimi
- XML tabanlı MobilDev API entegrasyonu
- Spring Boot 3.x desteği
- Exception handling
- Request/Response loglama
- Correlation ID desteği
- Özelleştirilebilir konfigürasyon

## Gereksinimler

- Java 17 veya üzeri
- Spring Boot 3.x
- Maven

## Kurulum

1. Kütüphaneyi local Maven repository'nize ekleyin:

```bash
mvn clean install
```

2. Projenizin `pom.xml` dosyasına dependency ekleyin:

```xml
	<groupId>org.halilkrkn</groupId>
	<artifactId>sms-multisender-lib</artifactId>
	<version>1.0.1</version>
```

## Konfigürasyon

1. `application.yml` dosyanıza aşağıdaki konfigürasyonu ekleyin:

```yaml
mobildev:
  sms:
    username: "your_username"
    password: "your_password"
    originator: "your_organitor"
    api-url: https://xmlapi.mobildev.com
```

2. Main sınıfınıza component scan ekleyin:

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.yourproject", "org.halilkrkn.sms-multisender-lib"})
public class YourApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }
}
```

## Kullanım

### 1. Service Injection

```java
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SmsSenderService smsSenderService;
    
    // Service metodları...
}
```

### 2. Tekli SMS Gönderimi

```java
SmsMessage message = SmsMessage.builder()
        .phoneNumber("5551234567")
        .messageBody("Test message")
        .build();

smsSenderService.sendMultipleMessages(List.of(message));
```

### 3. Toplu SMS Gönderimi

```java
List<SmsMessage> messages = List.of(
    SmsMessage.builder()
        .phoneNumber("5551234567")
        .messageBody("Message 1")
        .build(),
    SmsMessage.builder()
        .phoneNumber("5557654321")
        .messageBody("Message 2")
        .build()
);

smsSenderService.sendMultipleMessages(messages);
```

### 4. Controller Örneği

```java
@RestController
@RequestMapping("/api/v1/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsSenderService smsSenderService;

    @PostMapping("/send")
    public ResponseEntity<SmsResponse> sendSms(@Valid @RequestBody List<SmsRequest> requests) {
        List<SmsMessage> messages = requests.stream()
                .map(req -> SmsMessage.builder()
                        .phoneNumber(req.getPhoneNumber())
                        .messageBody(req.getMessageBody())
                        .build())
                .collect(Collectors.toList());

        smsSenderService.sendMultipleMessages(messages);
        return ResponseEntity.ok(/* ... */);
    }
}
```

### 5. API Örnek Request

```json
[
  {
    "phoneNumber": "5551234567",
    "messageBody": "Test message 1"
  },
  {
    "phoneNumber": "5557654321",
    "messageBody": "Test message 2"
  }
]
```

## Exception Handling

Kütüphane şu durumlarda exception fırlatır:

- Invalid credentials
- API connection errors
- Invalid phone number format
- Empty message content

Örnek exception handling:

```java
try {
    smsSenderService.sendMultipleMessages(messages);
} catch (Exception e) {
    log.error("SMS sending failed: ", e);
    // Handle error...
}
```

## Logging

Kütüphane SLF4J kullanır. Log seviyesini ayarlamak için:

```yaml
logging:
  level:
    com.mobildev.sms: DEBUG
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

[MIT License](LICENSE)
