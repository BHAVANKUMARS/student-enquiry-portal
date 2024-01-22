package com.ashokit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail(String from,String to,String subject,String body) {

        boolean isMailSent = false;

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);

            messageHelper.setFrom(from);

            messageHelper.setSubject(subject);

            messageHelper.setTo(to);

            messageHelper.setText("<h1>Your Password :  " + body + "</h1>", true);

            javaMailSender.send(mimeMessage);

            isMailSent = true;

        }catch(Exception e){

            LOGGER.error(e.getMessage());

        }

        return isMailSent;

    }
}
