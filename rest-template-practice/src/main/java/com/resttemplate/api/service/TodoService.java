package com.resttemplate.api.service;

import com.resttemplate.api.model.TodoDto;
import com.resttemplate.api.model.TodoRequestDto;
import com.resttemplate.api.model.TodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final RestTemplate restTemplate;

    public List<TodoDto> getAllTodos() {
        TodoResponseDto todoResponseDto = restTemplate.getForObject("/todos", TodoResponseDto.class);
        return Optional.ofNullable(todoResponseDto)
                .map(TodoResponseDto::getTodos)
                .orElse(new ArrayList<>());
    }

    public TodoDto getTodo(String id) {
        return restTemplate.getForObject("/todos/" + id, TodoDto.class);
    }

    public TodoDto getRandomTodo() {
        return restTemplate.getForObject("/todos/random", TodoDto.class);
    }

    public TodoDto[] getRandomTodo(int maxLength) {
        return restTemplate.getForObject("/todos/random/" + maxLength, TodoDto[].class);
    }

    public TodoDto addTodo(TodoRequestDto.Create todoCreateDto) {
        return restTemplate.postForObject("/todos/add", todoCreateDto, TodoDto.class);
    }

    public void updateTodo(String id, TodoRequestDto.Update todoDto) {
        restTemplate.put("/todos/" + id, todoDto);
    }

    public void deleteTodo(String id) {
        restTemplate.delete("/todos/" + id);
    }

}
