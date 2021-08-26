package com.senob.userapi.accounts.controller;

import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.accounts.dto.AccountOrderDto;
import com.senob.userapi.accounts.dto.AccountSearch;
import com.senob.userapi.accounts.service.AccountService;
import com.senob.userapi.orders.Order;
import com.senob.userapi.orders.OrderDto;
import com.senob.userapi.orders.service.OrderService;
import com.sun.istack.Nullable;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;

    private final OrderService orderService;

    @PostMapping("/signup")
    public ResponseEntity<AccountDto> signup(@Valid @RequestBody AccountDto accountDto) throws DuplicateMemberException {
        return ResponseEntity.ok(accountService.signup(accountDto));
    }

    @GetMapping("/{account_id}")
    public ResponseEntity<AccountDto> getAccount(@Positive(message = "account_id를 입력해주세요.") @PathVariable(value = "account_id") Long account_id) {
        return ResponseEntity.ok(accountService.getAccount(account_id));
    }

    @GetMapping("/{account_id}/orders")
    public ResponseEntity<List<OrderDto>> getOrders(@Positive(message = "account_id를 입력해주세요.") @PathVariable(value = "account_id") Long account_id) {
        return ResponseEntity.ok(orderService.getOrders(account_id));
    }

    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountOrderDto>> getAccounts(AccountSearch accountSearch,
                                                             @PageableDefault(size = 5, sort = "username") Pageable pageable) {
        return ResponseEntity.ok(accountService.getPagableAccounts(accountSearch, pageable));
    }

}
