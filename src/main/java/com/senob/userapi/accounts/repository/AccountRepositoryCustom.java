package com.senob.userapi.accounts.repository;

import com.senob.userapi.accounts.dto.AccountDto;
import com.senob.userapi.accounts.dto.AccountOrderDto;
import com.senob.userapi.accounts.dto.AccountSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepositoryCustom {

    Page<AccountOrderDto> searchAll(AccountSearch search, Pageable pageable);

}
