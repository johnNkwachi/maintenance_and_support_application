package arnergy.solar.exception;

import arnergy.solar.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> userAlreadyExistException(RegistrationException registrationException,
                                                       HttpServletRequest httpServletRequest){
        ApiResponse apiResponse =  ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .isSuccessful(false)
                .path(httpServletRequest.getRequestURI())
                .data(registrationException.getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> userAlreadyExistException(ExecutionControl.UserException userException,
                                                       HttpServletRequest httpServletRequest){
        ApiResponse apiResponse =  ApiResponse.builder()
                .timestamp(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .isSuccessful(false)
                .path(httpServletRequest.getRequestURI())
                .data(userException.getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
