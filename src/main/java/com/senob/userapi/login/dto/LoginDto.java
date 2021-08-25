package com.senob.userapi.login.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    @NotEmpty(message = "이메일을 입력해주세요.")
    @Email(regexp = "^\\w+@\\w+\\.\\w+(\\.\\w+$)?", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotEmpty(message = "패스워드를 입력해주세요.")
    private String password;
}
