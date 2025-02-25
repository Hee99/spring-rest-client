package com.restclient.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private static final Logger log = LoggerFactory.getLogger(RestClientConfig.class);

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory())
                .defaultStatusHandler(
                        HttpStatusCode::is4xxClientError,
                        (request, response) -> {
                            log.error("Client Error Code={}", response.getStatusCode());
                            log.error("Client Error Message={}", new String(response.getBody().readAllBytes()));
                        })
                .defaultStatusHandler(
                        HttpStatusCode::is5xxServerError,
                        (request, response) -> {
                            log.error("Server Error Code={}", response.getStatusCode());
                            log.error("Server Error Message={}", new String(response.getBody().readAllBytes()));
                        })
                .baseUrl("https://dummyjson.com")
                .build();
    }

}
