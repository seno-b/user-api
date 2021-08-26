package com.senob.userapi.orders;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String productName;
    private LocalDateTime paymentDate;

    public OrderDto(Order order){
        this.id = order.getId();
        this.productName = order.getProductName();
        this.paymentDate = order.getPaymentDate();
    }
}
