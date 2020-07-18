package fr.laPouleQuiMousse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

@Component
public class EmailService {

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.from.name}")
    private String name;

    @Autowired
    public JavaMailSender sender;

    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(new InternetAddress(from, name));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            sender.send(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void sendSimpleMessageWithAttachments(String to, String subject, String text, ArrayList<MultipartFile> files) throws MessagingException {
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress(from, name));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            for (MultipartFile multipartFile: files){
                helper.addAttachment(multipartFile.getOriginalFilename(), new InputStreamSource() {
                    @Override
                    public InputStream getInputStream() throws IOException {
                        return multipartFile.getInputStream();
                    }
                });
            }
            sender.send(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
