package com.example.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("order")
public class ItemOrder {
    @Id
    private String id; //주문id
    private String item; //판매물품
    private Integer count; //갯수
    private Long totalPrice; //총액
    private String status; //주문상태
}
