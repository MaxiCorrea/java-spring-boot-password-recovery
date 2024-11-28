package com.maxicorrea.java_spring_boot_password_recovery.shared;

import java.io.IOException;

import org.springframework.stereotype.Service;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

    public void sendWelcome(
            final String email,
            final String username) throws IOException {
        try {
            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(buildWelcomeMail(email, username));
            sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    private String buildWelcomeMail(
            final String email,
            final String username) throws IOException {
        Email from = new Email(System.getenv("SENDGRID_SENDER_EMAIL"));
        Email to = new Email(email);
        String value = String.format("Welcome %s !", username);
        Content content = new Content("text/plain", value);
        String subject = "User Register";
        Mail mail = new Mail(from, subject, to, content);
        return mail.build();
    }

    public void sendRecovery(
            final String email,
            final String token) throws IOException {
        try {
            SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(buildRecoveryMail(email, token));
            sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }

    private String buildRecoveryMail(
            final String email,
            final String token) throws IOException {
        Email from = new Email(System.getenv("SENDGRID_SENDER_EMAIL"));
        Email to = new Email(email);
        String value = String.format("Recovery Password using token -> %s !", token);
        Content content = new Content("text/plain", value);
        String subject = "Password Recovery";
        Mail mail = new Mail(from, subject, to, content);
        return mail.build();
    }
}
