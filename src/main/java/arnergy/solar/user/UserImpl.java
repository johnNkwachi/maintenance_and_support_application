package arnergy.solar.user;

import arnergy.solar.exception.RegistrationException;
import arnergy.solar.exception.UserException;
import arnergy.solar.registration.tokens.ConfirmationToken;
import arnergy.solar.registration.tokens.ConfirmationTokenService;
import arnergy.solar.user.dtos.LoginRequest;
import arnergy.solar.user.dtos.PasswordResetRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Builder
@Slf4j
public class UserImpl implements UserService{
    private final UserRepository userRepository;
    private final ConfirmationTokenService tokenService;

    @Autowired
    public UserImpl(UserRepository userRepository, ConfirmationTokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public String createAccount(User user) {
        var saveUser = userRepository.save(user);
        return generateToken(saveUser);
    }
    private String generateToken(User savedUser) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                savedUser
        );
        tokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    @Override
    public Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress) {
        return userRepository.findByEmailAddressIgnoreCase(emailAddress);
    }

    @Override
    public boolean enableUser(String email) {
        return false;
    }

    @Override
    public User saveUser(User foundUser) {

        return userRepository.save(foundUser);
    }

    @Override
    public String login(LoginRequest loginRequest) {
        var foundUser = findUserByEmailAddressIgnoreCase(loginRequest.getEmail())
                .orElseThrow(()-> new UserException(String.format("%s email does not exist", loginRequest.getEmail())));

        if(!foundUser.getPassword().equals(loginRequest.getPassword())) throw new UserException("Invalid email or Password");
        if(foundUser.isDisabled()) throw new UserException("User is not enabled, please verify your email");

        return ("Logged in Successful");
    }

    @Override
    public String generateToken(String email) {
        var foundUser =  findUserByEmailAddressIgnoreCase(email)
                .orElseThrow(()-> new RegistrationException(email + " does not exist."));
        return generateToken(saveUser(foundUser));
    }

    @Override
    public String changePassword(PasswordResetRequest passwordReset) {
        return null;
    }
    @Override
    public User getUserById(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new UserException("User not found with ID: " + userId));
    }
}
