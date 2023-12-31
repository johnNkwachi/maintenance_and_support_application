package arnergy.solar.registration.tokens;

import arnergy.solar.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@EnableScheduling
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
   private final ConfirmationTokenRepository tokenRepository;
   @Autowired
    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmToken) {
        this.tokenRepository.save(confirmToken);
    }

    @Override
    public void getConfirmationToken(String confirmToken) {
       tokenRepository.findByToken(confirmToken);
    }

    @Override
    @Scheduled(cron = "0 00 00 * * *")
    public void deleteExpiredConfirmationToken() {
        System.out.println("Deleted");
        tokenRepository.deleteConfirmationTokensByExpiredAtBefore(LocalDateTime.now());

    }

    @Override
    public ConfirmationToken confirmAccessToken(String confirmationToken) {
        System.out.println(confirmationToken);
        Optional<ConfirmationToken> optionalToken = tokenRepository.findByToken(confirmationToken);
        if (optionalToken.isPresent()) {
            return optionalToken.get();
        } else {
            throw new RegistrationException("Token Does Not Exist");
        }
    }

    @Override
    public boolean setConfirmedAt(String token) {
        tokenRepository.setConfirmedAt(LocalDateTime.now(), token);
        return true;
    }
}
