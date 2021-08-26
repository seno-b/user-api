package com.senob.userapi.accounts.dto;

import com.senob.userapi.accounts.GenderType;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
public class AccountDto {

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    @Size(min = 2, max = 20, message = "이름은 2 ~ 20자 까지 허용됩니다.")
    @Pattern(regexp = "^[가-힣]*|[a-zA-Z]*$", message = "이름은 한글, 영문 대소문자만 허용됩니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 10 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotEmpty(message = "닉네임은 필수 입력값 입니다.")
    @Size(max = 30, message = "닉네임은 최대 30자 까지 허용됩니다.")
    @Pattern(regexp = "^[a-z]*$", message = "닉네임은 영문 소문자만 허용됩니다.")
    private String nickname;

    @Positive(message = "전화번호는 필수 입력값 입니다.")
    @Digits(integer = 20, fraction = 0, message = "전화번호는 최대 20자 까지 허용됩니다.")
    private BigDecimal tel;

    @NotEmpty(message = "이메일은 필수 입력값 입니다.")
    @Size(max = 100, message = "이메일은 최대 100자 까지 허용됩니다.")
    @Email(regexp = "^\\w+@\\w+\\.\\w+(\\.\\w+$)?", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private GenderType gender;

    public AccountDto(String username, String password, String nickname, BigDecimal tel, String email, GenderType gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
    }
}
