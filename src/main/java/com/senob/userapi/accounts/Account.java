package com.senob.userapi.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {

    @Id @GeneratedValue
    @Column(name = "account_id")
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

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "account_authority"
            , joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")}
            , inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
