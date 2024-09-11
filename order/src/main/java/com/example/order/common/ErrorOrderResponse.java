package com.example.order.common;

import lombok.Getter;

@Getter
public class ErrorOrderResponse implements OrderResponse {
    private final String message;

    public ErrorOrderResponse(String message) {
        this.message = message;
    }
}
