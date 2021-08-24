package com.senob.userapi.security;

import com.senob.userapi.accounts.Account;
import com.senob.userapi.accounts.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findOneWithAuthoritiesByUsername(username)
                .map(account -> createUser(username, account))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 등록되지 않은 회원입니다."));
    }

    private User createUser(String username, Account account) {
        if (!account.isActivated()) {
            throw new RuntimeException(username + " -> 활성화되지 않은 회원입니다.");
        }
        List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(account.getUsername()
                        , account.getPassword()
                        , grantedAuthorities);
    }
}
