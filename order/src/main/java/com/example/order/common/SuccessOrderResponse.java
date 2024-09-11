package com.example.order.common;

import com.example.order.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter

public class SuccessOrderResponse implements OrderResponse {
    @JsonUnwrapped
    private final OrderDTO orderDTO;
    public SuccessOrderResponse(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }
}
