package com.smartcode.ecommerce.service.mail;

public interface MailService {

    void sendEmail(String email, String subject, String message);

}
