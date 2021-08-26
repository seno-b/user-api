package com.senob.userapi.accounts.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.senob.userapi.accounts.Account;
import com.senob.userapi.accounts.dto.AccountOrderDto;
import com.senob.userapi.accounts.dto.AccountSearch;
import com.senob.userapi.accounts.dto.QAccountOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.senob.userapi.accounts.QAccount.account;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom{

    private final EntityManager em;

    @Override
    public Page<AccountOrderDto> searchAll(AccountSearch search, Pageable pageable) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        List<AccountOrderDto> content = queryFactory
                .select(new QAccountOrderDto(
                        account.id,
                        account.username,
                        account.email,
                        account.nickname,
                        account.tel))
                .from(account)
                .where(usernameEq(search.getUsername()),
                        emailEq(search.getEmail()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Account> countQuery = queryFactory
                .select(account)
                .from(account)
                .where(usernameEq(search.getUsername()),
                        emailEq(search.getEmail()));

        return PageableExecutionUtils.getPage(content, pageable,
                countQuery::fetchCount);
    }

    private BooleanExpression usernameEq(String username) {
        if (ObjectUtils.isEmpty(username)) {
            return null;
        }
        return account.username.eq(username);
    }

    private BooleanExpression emailEq(String email) {
        if (ObjectUtils.isEmpty(email)) {
            return null;
        }
        return account.username.eq(email);
    }
}
