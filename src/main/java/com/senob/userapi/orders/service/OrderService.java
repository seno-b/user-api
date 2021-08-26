package com.senob.userapi.orders.service;

import com.senob.userapi.orders.Order;
import com.senob.userapi.orders.OrderDto;
import com.senob.userapi.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderDto> getOrders(Long accountId) {
        List<Order> orders = orderRepository.findByAccountId(accountId);

        List<OrderDto> result = orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());

        return result;
    }
}