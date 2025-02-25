package com.restclient.api.service;

import com.restclient.api.model.TodoDto;
import com.restclient.api.model.TodoRequestDto;
import com.restclient.api.model.TodoResponseDto;
import com.restclient.config.TodoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoApiClient todoApiClient;

    public List<TodoDto> getAllTodos() {
        TodoResponseDto responseDto = todoApiClient.sendGet("/todossss", TodoResponseDto.class);
        return Optional.ofNullable(responseDto)
                .map(TodoResponseDto::getTodos)
                .orElse(new ArrayList<>());
    }

    public TodoDto getTodo(String id) {
        return todoApiClient.sendGet("/todos/" + id, TodoDto.class);
    }

    public TodoDto getRandomTodo() {
        return todoApiClient.sendGet("/todos/random", TodoDto.class);
    }

    public TodoDto[] getRandomTodo(int maxLength) {
        return todoApiClient.sendGet("/todos/random/" + maxLength, TodoDto[].class);
    }

    public TodoDto addTodo(TodoRequestDto.Create todoCreateDto) {
        return todoApiClient.sendPost("/todos/add", todoCreateDto, TodoDto.class);
    }

    public void updateTodo(String id, TodoRequestDto.Update todoDto) {
        todoApiClient.sendPut("/todos/" + id, todoDto, Void.class);
    }

    public void deleteTodo(String id) {
        todoApiClient.sendDelete("/todos/" + id, Void.class);
    }

}
