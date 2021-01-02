package com.codingfreeks.RedditClone.service;

import com.codingfreeks.RedditClone.exceptions.SpringRedditException;
import com.codingfreeks.RedditClone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private JavaMailSender javaMailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail) throws SpringRedditException {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(notificationEmail.getRecipient());
            mimeMessageHelper.setFrom("springReddit@gmail.com");
            mimeMessageHelper.setSubject(notificationEmail.getSubjct());
            mimeMessageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            javaMailSender.send(mimeMessagePreparator);
            log.info("Sent email !!!");
        } catch (MailException exc) {
            exc.printStackTrace();
            throw new SpringRedditException("Exception while sending mail to " + notificationEmail.getRecipient());
        }

    }
}
