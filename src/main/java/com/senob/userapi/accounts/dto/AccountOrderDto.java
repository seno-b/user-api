package com.senob.userapi.accounts.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountOrderDto {

    private Long accountId;
    private String username;
    private String email;
    private String nickname;
    private BigDecimal tel;

    @QueryProjection
    public AccountOrderDto(Long accountId, String username, String email, String nickname, BigDecimal tel) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.tel = tel;
    }
}
