package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    private SimpleMailMessage email;

    @Test
    void testEnviarEmail() {

        this.email = new SimpleMailMessage();
        email.setFrom("adopet@email.com.br");
        email.setTo("To");
        email.setSubject("Subject");
        email.setText("Content");

        emailService.enviar("To", "Subject", "Content");

        then(javaMailSender).should().send(email);
    }
}