package com.senob.userapi.accounts.service;

import com.senob.userapi.accounts.Account;
import com.senob.userapi.accounts.Authority;
import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.accounts.dto.AccountOrderDto;
import com.senob.userapi.accounts.dto.AccountSearch;
import com.senob.userapi.accounts.repository.AccountRepository;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public AccountDto signup(AccountDto accountDto) throws DuplicateMemberException {
        if (accountRepository.findOneWithAuthoritiesByEmail(accountDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Account account = Account.builder()
                .username(accountDto.getUsername())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .nickname(accountDto.getNickname())
                .tel(accountDto.getTel())
                .email(accountDto.getEmail())
                .gender(accountDto.getGender())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        accountRepository.save(account);

        return accountDto;
    }

    @Transactional(readOnly = true)
    public AccountDto getAccount(Long account_id) {
        Optional<Account> oneByEmail = accountRepository.findById(account_id);
        if( oneByEmail.isPresent() ){
            Account account = oneByEmail.get();
            return account.convertToAccountDto();
        }

        return null;
    }

    public Page<AccountOrderDto> getPagableAccounts(AccountSearch accountSearch, Pageable pageable) {

        Page<AccountOrderDto> accountOrderDtos = accountRepository.searchAll(accountSearch, pageable);

        System.out.println("accountOrderDtos = " + accountOrderDtos);

        return accountOrderDtos;

    }
}
