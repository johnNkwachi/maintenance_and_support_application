package arnergy.solar.registration.tokens;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String confirmToken);

    void deleteConfirmationTokensByExpiredAtBefore(LocalDateTime currentTime);

//    @Modifying
    @Query("UPDATE ConfirmationToken confirmationToken " +
            "SET confirmationToken.confirmedAt = ?1 " +
            "WHERE confirmationToken.token = ?2 "
    )

    void setConfirmedAt(LocalDateTime now, String token);
}
