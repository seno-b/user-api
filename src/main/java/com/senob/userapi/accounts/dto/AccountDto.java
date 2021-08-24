package com.senob.userapi.accounts.dto;

import com.senob.userapi.accounts.GenderType;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Size(max = 20)
    private int tel;

    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    private GenderType gender;
}
