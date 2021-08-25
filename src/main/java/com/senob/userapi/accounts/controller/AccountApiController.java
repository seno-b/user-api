package com.senob.userapi.accounts.controller;

import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.accounts.service.AccountService;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(@Valid @RequestBody AccountDto accountDto) throws DuplicateMemberException {
        return ResponseEntity.ok(accountService.signup(accountDto));
    }

    @GetMapping("/{email:.+}")
    public ResponseEntity<AccountDto> getAccount(@NotEmpty(message = "이메일을 입력해주세요.") @PathVariable(value = "email") String email) throws DuplicateMemberException {
        return ResponseEntity.ok(accountService.getAccount(email));
    }

}
