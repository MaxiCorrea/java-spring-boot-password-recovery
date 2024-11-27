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

    private final String senderEmail;
    private final String apiKey;

    public EmailService() {
        senderEmail = System.getenv("SENDGRID_SENDER_EMAIL");
        apiKey = System.getenv("SENDGRID_API_KEY");
    }

    public void sendWelcome(
            final String email,
            final String username) throws IOException {

        Email from = new Email(senderEmail);
        Email to = new Email(email);
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        String subject = "Sending with SendGrid is Fun";
        Mail mail = new Mail(from, subject, to, content);

        try {
            SendGrid sg = new SendGrid(apiKey);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            throw ex;
        }
    }
}
