package org.halilkrkn.smsmultisenderlib.service;


import org.halilkrkn.smsmultisenderlib.entity.SmsMessage;

import java.util.List;

public interface SmsService {
    /**
     * Verilen SMS mesajlarını API’ye gönderir.
     *
     * @param messages Gönderilecek SMS mesajlarının listesi.
     * @return API’den gelen yanıt.
     */
    String sendSms(List<SmsMessage> messages);
}