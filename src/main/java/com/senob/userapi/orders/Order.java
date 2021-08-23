package com.senob.userapi.orders;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(length = 12)
    private Long id;

    @Column(length = 100)
    private String productName;

    @CreatedDate
    private LocalDateTime paymentDate;

}

