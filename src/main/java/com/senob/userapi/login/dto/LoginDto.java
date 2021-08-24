package com.senob.userapi.login.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(min = 10, max = 100)
    private String password;
}
