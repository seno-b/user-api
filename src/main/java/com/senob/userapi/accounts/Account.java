package com.senob.userapi.accounts;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String username;

    @Column(length = 30)
    private String nickname;

    @Column(length = 100)
    private String password;

    @Column(length = 20)
    private int tel;

    @Column(length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private GenderType gender;
}
