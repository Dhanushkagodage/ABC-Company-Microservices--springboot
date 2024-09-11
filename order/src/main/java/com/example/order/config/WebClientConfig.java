package com.example.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }

    @Bean
    public WebClient inventoryWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8080/api/ABC").build();
    }

    @Bean
    public WebClient productWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8082/api/ABC").build();
    }
}
