package com.restclient.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class TodoApiClient {

    private final RestClient restClient;

    public <T> T sendGet(String uri, Class<T> responseType) {
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(responseType);
    }

    public <T> T sendPost(String uri, Object body, Class<T> responseType) {
        return restClient.post()
                .uri(uri)
                .body(body)
                .retrieve()
                .body(responseType);
    }

    public <T> T sendPut(String uri, Object body, Class<T> responseType) {
        return restClient.put()
                .uri(uri)
                .body(body)
                .retrieve()
                .body(responseType);
    }

    public <T> T sendDelete(String uri, Class<T> responseType) {
        return restClient.delete()
                .uri(uri)
                .retrieve()
                .body(responseType);
    }

}
