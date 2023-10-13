package arnergy.solar.registration;

import arnergy.solar.registration.dtos.ConfirmationTokenRequest;
import arnergy.solar.registration.dtos.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface RegistrationService {
    String register (RegistrationRequest request) throws MessagingException;
    public String confirmToken(ConfirmationTokenRequest request);
    String resendToken(String email) throws MessagingException;
    String resetPassword(String email) throws MessagingException;
}
