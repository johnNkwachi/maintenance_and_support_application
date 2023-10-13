package arnergy.solar.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class EmailService implements EmailSender{
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Override
    @Async
    public void send(String to, String email) throws MessagingException {
        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper  mimeMessageHelper = new MimeMessageHelper(mailMessage, mailMessage.getEncoding());
            mimeMessageHelper.setSubject("Confirm your email");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom("jnkwachi@gmail.com");
            mimeMessageHelper.setText(email, true);
            mailSender.send(mailMessage);

        } catch (MessagingException e) {
            log.error("Problem sending email (MessagingException): {}", e.getMessage(), e);
            log.info(e.getMessage());
        } catch (MailException e) {
            log.error("Problem sending email (MessagingException): {}", e.getMessage(), e);
            log.info(e.getMessage());
        }


    }
}
