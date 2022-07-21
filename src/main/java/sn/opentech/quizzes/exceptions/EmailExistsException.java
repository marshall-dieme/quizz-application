package sn.opentech.quizzes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }

    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<String> handleEmailExist() {
        return new ResponseEntity<String>("EmailAlreadyExist", HttpStatus.CONFLICT);
    }
}
