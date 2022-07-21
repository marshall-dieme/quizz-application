package sn.opentech.quizzes.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import sn.opentech.quizzes.dto.UserDto;
import sn.opentech.quizzes.dto.UserRequest;
import sn.opentech.quizzes.events.OnRegistrationCompleteEvent;
import sn.opentech.quizzes.exceptions.UserAlreadyExistException;
import sn.opentech.quizzes.model.User;
import sn.opentech.quizzes.model.VerificationToken;
import sn.opentech.quizzes.service.UserService;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class UserController {

    private final UserService userService;

    private final ApplicationEventPublisher publisher;


    public UserController(UserService userService, ApplicationEventPublisher publisher) {
        this.userService = userService;
        this.publisher = publisher;
    }
    

    @PostMapping(value="/login")
    public UserDetails login(@RequestBody @Valid UserRequest user) {
        return userService.loadUserByUsername(user.getEmail());
    }
    
    @PostMapping(value="/register")
    public ResponseEntity<String> registration(@RequestBody @Valid UserDto dto, HttpServletRequest request, Errors errors) throws UserAlreadyExistException {
        User user = userService.register(dto);
        if (user == null) {
            throw new UserAlreadyExistException();
        }

        String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();

        publisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    

    @GetMapping(value="/registrationConfirm")
    public ResponseEntity<String> confirmRegistration(@RequestParam("token") String token, WebRequest request) {
        
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.UNAUTHORIZED);
        }

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            return new ResponseEntity<String>("Token expired", HttpStatus.UNAUTHORIZED);
        }

        user.setEnabled(true);
        userService.saveUser(user);
        return new ResponseEntity<String>("Successfully activated", HttpStatus.CREATED);
    }
    
    
}