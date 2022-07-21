package sn.opentech.quizzes.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import sn.opentech.quizzes.annotations.PasswordMatches;
import sn.opentech.quizzes.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        UserDto user = (UserDto) value;
        return user.getPassword().equals(user.getMatchingPassword());
    }
    
}
