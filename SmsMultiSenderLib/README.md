## Yapılandırma Bilgileri

Uygulamanızın *Maven Dependency ve `application.yml` dosyasına aşağıdaki yapılandırmaları ekleyin:

```xml
<dependency>
    <groupId>com.mobildev</groupId>
    <artifactId>sms-library</artifactId>
    <version>1.0.0</version>
</dependency>


mobildev:
  sms:
    username: YOUR_USERNAME       # API kullanıcı adı
    password: YOUR_PASSWORD       # API şifresi
    originator: MDEV_DEMO         # Alfanumerik gönderici adı
    api-url: https://xmlapi.mobildev.com  # API endpoint URL
