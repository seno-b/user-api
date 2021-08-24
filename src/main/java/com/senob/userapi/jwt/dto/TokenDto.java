package com.senob.userapi.jwt.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String token;
}
