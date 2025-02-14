package com.webclient.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TodoApiClient {

    private final WebClient webClient;

    public <T> T sendGet(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendPost(String uri, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendPut(String uri, Object body, Class<T> responseType) {
        return webClient.put()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendDelete(String uri, Class<T> responseType) {
        return webClient.delete()
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

}
