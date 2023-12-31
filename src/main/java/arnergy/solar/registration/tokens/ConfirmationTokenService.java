package arnergy.solar.registration.tokens;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken confirmToken);
    void getConfirmationToken(String confirmToken);
    void deleteExpiredConfirmationToken();
    ConfirmationToken confirmAccessToken(String confirmationToken);
    boolean setConfirmedAt(String token);
}
