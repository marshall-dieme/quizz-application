package sn.opentech.quizzes.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.opentech.quizzes.dto.UserDto;
import sn.opentech.quizzes.mapper.UserMapper;
import sn.opentech.quizzes.model.User;
import sn.opentech.quizzes.model.VerificationToken;
import sn.opentech.quizzes.repository.UserRepository;
import sn.opentech.quizzes.repository.VerificationTokenRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper mapper;

    private final UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;

    public UserService(UserRepository userRepository, VerificationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with email : " + email);
        }

        return user;
    }

    public User register(@Valid UserDto dto) {
        return userRepository.save(mapper.toEntity(dto));
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
    

    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }
}