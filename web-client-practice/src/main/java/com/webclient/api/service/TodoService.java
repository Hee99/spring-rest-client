package com.webclient.api.service;

import com.webclient.api.model.TodoDto;
import com.webclient.api.model.TodoRequestDto;
import com.webclient.api.model.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final WebClient webClient;

    public List<TodoDto> getAllTodos() {
        return webClient.get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(TodoResponseDto.class)
                .map(TodoResponseDto::getTodos)
                .block();
    }

    public TodoDto getTodo(String id) {
        return webClient.get()
                .uri("/todos/" + id)
                .retrieve()
                .bodyToMono(TodoDto.class)
                .block();
    }

    public TodoDto getRandomTodo() {
        return webClient.get()
                .uri("/todos/random")
                .retrieve()
                .bodyToMono(TodoDto.class)
                .block();
    }

    public TodoDto[] getRandomTodo(int maxLength) {
        return webClient.get()
                .uri("/todos/random/" + maxLength)
                .retrieve()
                .bodyToMono(TodoDto[].class)
                .block();
    }

    public TodoDto addTodo(TodoRequestDto.Create todoCreateDto) {
        return webClient.post()
                .uri("/todos/add")
                .bodyValue(todoCreateDto)
                .retrieve()
                .bodyToMono(TodoDto.class)
                .block();
    }

    public void updateTodo(String id, TodoRequestDto.Update todoDto) {
        webClient.put()
                .uri("/todos/" + id)
                .bodyValue(todoDto)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteTodo(String id) {
        webClient.delete()
                .uri("/todos/" + id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}
