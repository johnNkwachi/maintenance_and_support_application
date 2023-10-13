package arnergy.solar.user;

import arnergy.solar.user.dtos.LoginRequest;
import arnergy.solar.user.dtos.PasswordResetRequest;
import arnergy.solar.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("api/v1/login")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                                   HttpServletRequest httpServletRequest){

        var loggedUser = userService.login(loginRequest);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(loggedUser).
                build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordReset,
                                           HttpServletRequest httpServletRequest){

        var loggedUser = userService.changePassword(passwordReset);

        ApiResponse response = ApiResponse.builder().
                status(HttpStatus.CREATED.value())
                .isSuccessful(true)
                .timestamp(ZonedDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .data(loggedUser).
                build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

}
