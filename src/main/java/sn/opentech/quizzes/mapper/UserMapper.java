package sn.opentech.quizzes.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import sn.opentech.quizzes.dto.UserDto;
import sn.opentech.quizzes.model.User;

@Component
public class UserMapper {


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(UserDto dto) {
        User user = new User();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEnabled(false);

        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());

        return dto;
    }
}