package arnergy.solar.registration.tokens;

import arnergy.solar.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document
public class ConfirmationToken {
    @Id
    private String id;
    private  String token;
    private LocalDateTime createdAt;
    private  LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
//    @DBRef(name = "userId", referencedColumnName = "id")
    @DBRef
    private User user;
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiredAt, User savedUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = savedUser;
    }
}
