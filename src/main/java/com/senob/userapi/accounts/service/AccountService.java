package com.senob.userapi.accounts.service;

import com.senob.userapi.accounts.Account;
import com.senob.userapi.accounts.Authority;
import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.accounts.repository.AccountRepository;
import com.senob.userapi.util.SecurityUtil;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account signup(AccountDto accountDto) throws DuplicateMemberException {
        if (accountRepository.findOneWithAuthoritiesByUsername(accountDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        //빌더 패턴의 장점
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .nickname(accountDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public Optional<Account> getUserWithAuthorities(String username) {
        return accountRepository.findOneWithAuthoritiesByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Account> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(accountRepository::findOneWithAuthoritiesByUsername);
    }

}
