package com.itamnesia.bhcrud.service.impl;

import com.itamnesia.bhcrud.config.properties.SmsCredentials;
import com.itamnesia.bhcrud.exception.UnavailableServiceException;
import com.itamnesia.bhcrud.service.ConfirmationSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Service
@RequiredArgsConstructor
public class ConfirmationSenderImpl implements ConfirmationSender {

    private static final String SERVICE_URL = "https://api.smstraffic.ru/multi.php";
    private static final String DUPLICATE_SERVICE_URL = "https://api2.smstraffic.ru/multi.php";

    private final RestTemplate restTemplate;
    private final SmsCredentials smsCredentials;

    @Override
    @Async
    public void send(String phoneNumber, String code) {
        try {
            var entity = createRequestEntity(phoneNumber, code);
            restTemplate.postForObject(SERVICE_URL, entity, String.class);
        } catch (Exception e) {
            System.out.println("");
        }
    }

    private HttpEntity<LinkedMultiValueMap<String, String>> createRequestEntity(String phone, String content) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        var body = new LinkedMultiValueMap<String, String>();
        body.set("login", smsCredentials.getLogin());
        body.set("password", smsCredentials.getPassword());
        body.set("originator", smsCredentials.getOriginator());
        body.set("phones", phone);
        body.set("message", "Confirmation code: %s".formatted(content));
        body.set("rus", "5");
        return new HttpEntity<>(body, headers);
    }

    @XmlRootElement(name = "reply")
    private static class SmsResponse {
        @XmlElement(name = "result", required = true)
        private String result;
        @XmlElement(name = "code", required = true)
        private int code;
        @XmlElement(name = "description", required = true)
        private String description;

        public boolean isSuccessful() {
            return code == 0;
        }
    }
}


