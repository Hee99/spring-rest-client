package com.webclient.api.service;

import com.webclient.api.model.TodoDto;
import com.webclient.api.model.TodoRequestDto;
import com.webclient.api.model.TodoResponseDto;
import com.webclient.config.TodoApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoApiClient todoApiClient;

    public List<TodoDto> getAllTodos() {
        TodoResponseDto responseDto = todoApiClient.sendGet("/todos", TodoResponseDto.class);
        return responseDto.getTodos();
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
