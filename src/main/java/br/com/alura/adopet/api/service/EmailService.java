package br.com.alura.adopet.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviar(String to, String subject, String content) {

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo(to);
        email.setSubject(subject);
        email.setText(content);
        emailSender.send(email);
    }
}
