package sn.opentech.quizzes.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.opentech.quizzes.annotations.ValidEmail;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {
    
    @NotBlank
    @ValidEmail
    private String email;

    private String password;
}