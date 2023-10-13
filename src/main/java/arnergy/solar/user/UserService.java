package arnergy.solar.user;

import arnergy.solar.user.dtos.LoginRequest;
import arnergy.solar.user.dtos.PasswordResetRequest;

import java.util.Optional;

public interface UserService {
 public String createAccount(User user);
 Optional<User> findUserByEmailAddressIgnoreCase(String emailAddress);
 boolean enableUser(String email);
 User saveUser(User foundUser);
 String login(LoginRequest loginRequest);
 String generateToken(String email);
 String changePassword(PasswordResetRequest passwordReset);
 User getUserById(String userId);
}
