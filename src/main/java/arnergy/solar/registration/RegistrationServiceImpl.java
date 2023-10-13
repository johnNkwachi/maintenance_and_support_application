package arnergy.solar.registration;

import arnergy.solar.email.EmailSender;
import arnergy.solar.exception.RegistrationException;
import arnergy.solar.exception.UserException;
import arnergy.solar.registration.dtos.ConfirmationTokenRequest;
import arnergy.solar.registration.dtos.RegistrationRequest;
import arnergy.solar.registration.tokens.ConfirmationToken;
import arnergy.solar.registration.tokens.ConfirmationTokenService;
import arnergy.solar.user.User;
import arnergy.solar.user.UserRole;
import arnergy.solar.user.UserService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static jakarta.mail.Flags.Flag.USER;
@Service
public class RegistrationServiceImpl implements RegistrationService{
    private final UserService userService;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;

    public RegistrationServiceImpl(UserService userService, EmailSender emailSender, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public String register(RegistrationRequest request) throws MessagingException {
        var foundUser =  userService
                .findUserByEmailAddressIgnoreCase(request.getEmailAddress()).isPresent();
        if(foundUser) throw new RegistrationException(request.getEmailAddress() + " already Exists");

        String token =  userService.createAccount(
                new User(
                        request.getEmailAddress(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
        System.out.println(request.getEmailAddress());
        emailBuilder(request.getEmailAddress(), request.getFirstName(), token);
        return token;
    }
    private void emailBuilder(String email, String firstName, String token) throws MessagingException {
        emailSender.send(email, buildEmail(firstName, token));
    }

    @Override
    public String confirmToken(ConfirmationTokenRequest requestToken) {
        ConfirmationToken token= confirmationTokenService.confirmAccessToken(requestToken.getToken());
        if (token == null) {
            throw new RegistrationException("Token does not exist or is invalid.");
        }
//        var foundUser = userService.findUserByEmailAddressIgnoreCase(requestToken.getEmail())
//                .orElseThrow( ()-> new RegistrationException(String.format("%s does not exist", requestToken.getEmail())));
//
//        if(!foundUser.equals(token.getUser())) throw new RegistrationException("Token Entered does not Match.");
//        if(token.getExpiredAt().isBefore(LocalDateTime.now())) throw new RegistrationException("Token has Expired");
//        if(token.getConfirmedAt() != null) throw new RegistrationException("Token has been used");
//        confirmationTokenService.setConfirmedAt(token.getToken());
        enableUser(requestToken);
        return "Confirmed";
    }

    private void enableUser(ConfirmationTokenRequest confirmationTokenRequest) {
        userService.enableUser(confirmationTokenRequest.getEmail());
    }

    @Override
    public String resendToken(String email) throws MessagingException {
        var foundUser = userService.findUserByEmailAddressIgnoreCase(email)
                .orElseThrow(()-> new RegistrationException(email + " does not exit."));
        var token = userService.generateToken(foundUser.getEmailAddress());
        emailBuilder(foundUser.getEmailAddress(), foundUser.getFirstName(), token);
        return "Token Sent";
    }

    @Override
    public String resetPassword(String email) throws MessagingException {
        var foundUser = userService.findUserByEmailAddressIgnoreCase(email)
                .orElseThrow(() -> new UserException(String.format("%s email does not exist", email)));
        resendToken(foundUser.getEmailAddress());
        return "Token sent to Email for Confirmation";
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please copy the code below to confirm your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + link + "</p></blockquote>\n Link will expire in 10 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
