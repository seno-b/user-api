package com.senob.userapi.orders.repository;

import com.senob.userapi.orders.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "account")
    List<Order> findByAccountId(@Param("accountId") Long account_id);
}
