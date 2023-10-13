package arnergy.solar.email;

import jakarta.mail.MessagingException;
import org.springframework.context.annotation.Configuration;


public interface EmailSender {
    void send(String to, String email) throws MessagingException;
}
