package academy.softserve.eschool.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import academy.softserve.eschool.service.base.EmailServiceBase;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService implements EmailServiceBase{

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @NonNull
    private JavaMailSenderImpl mailSender;
    
    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        logger.info("Sending email to:[{}], subject:[{}], text:[{}]", to, subject, text);
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendHtmlMessage(String to, String subject, String text) {
        logger.info("Sending email to:[{}], subject:[{}], text:[{}]", to, subject, text);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            messageHelper.setSubject(subject);
            messageHelper.setTo(to);
            messageHelper.setText(text, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getLocalizedMessage(), e);
        }
    }
    
    

}
