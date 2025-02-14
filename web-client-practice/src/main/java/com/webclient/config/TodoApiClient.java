package com.webclient.config;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TodoApiClient {

    private final WebClient webClient;

    private Mono<? extends Throwable> handleError(ClientResponse response) {
        return response.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(
                    new ResponseStatusException(response.statusCode(), errorBody)
                ));
    }

    private boolean isError(HttpStatusCode status) {
        return status.is4xxClientError() || status.is5xxServerError();
    }

    public <T> T sendGet(String uri, Class<T> responseType) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(this::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendPost(String uri, Object body, Class<T> responseType) {
        return webClient.post()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .onStatus(this::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendPut(String uri, Object body, Class<T> responseType) {
        return webClient.put()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .onStatus(this::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
    }

    public <T> T sendDelete(String uri, Class<T> responseType) {
        return webClient.delete()
                .uri(uri)
                .retrieve()
                .onStatus(this::isError, this::handleError)
                .bodyToMono(responseType)
                .block();
    }

}
