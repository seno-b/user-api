package com.senob.userapi.accounts.dto;

import com.senob.userapi.accounts.GenderType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 10, max = 100)
    private String password;

    @NotNull
    @Size(max = 30)
    private String nickname;

    @NotNull
    private int tel;

    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    private GenderType gender;

    public AccountDto(String username, String password, String nickname, int tel, String email, GenderType gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.tel = tel;
        this.email = email;
        this.gender = gender;
    }
}
