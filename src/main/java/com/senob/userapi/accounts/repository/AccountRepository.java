package com.senob.userapi.accounts.repository;

import com.senob.userapi.accounts.Account;
import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.orders.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom{
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByEmail(String email);

    Optional<Account> findOneByEmail(String email);

    @Query(value = "select a from Account a")
    Page<AccountDto> findByUsernameOrEmail(String username, String email, Pageable pageable);
}
