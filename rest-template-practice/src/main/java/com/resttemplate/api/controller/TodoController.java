package com.resttemplate.api.controller;

import com.resttemplate.api.model.TodoDto;
import com.resttemplate.api.model.TodoRequestDto;
import com.resttemplate.api.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/todos")
    @Operation(description = "전체 할 일 목록을 조회합니다.")
    public ResponseEntity<?> getAllTodos() {
        return ResponseEntity.ok(todoService.getAllTodos());
    }

    @GetMapping("/todos/{id}")
    @Operation(description = "아이디(id)를 사용하여 할 일을 단건 조회합니다.")
    public ResponseEntity<?> getTodo(@PathVariable String id) {
        return ResponseEntity.ok(todoService.getTodo(id));
    }

    @GetMapping("/todos/random")
    @Operation(description = "할 일 단건을 랜덤으로 조회합니다.")
    public ResponseEntity<?> getTodoRandom() {
        return ResponseEntity.ok(todoService.getRandomTodo());
    }

    @GetMapping("/todos/random/{maxLength}")
    @Operation(description = "할 일 목록을 랜덤으로 조회합니다. 목록의 최대 길이(maxLength)를 제한합니다.")
    public ResponseEntity<?> getTodoRandom(@PathVariable int maxLength) {
        return ResponseEntity.ok(todoService.getRandomTodo(maxLength));
    }

    @PostMapping("/todos/add")
    @Operation(description = "새로운 할 일을 추가합니다.")
    public ResponseEntity<?> addTodo(@RequestBody TodoRequestDto.Create todoCreateDto) {
        return ResponseEntity.ok(todoService.addTodo(todoCreateDto));
    }

    @PutMapping("/todos/{id}")
    @Operation(description = "할 일을 수정합니다.")
    public ResponseEntity<?> updateTodo(@PathVariable String id, @RequestBody TodoRequestDto.Update todoDto) {
        todoService.updateTodo(id, todoDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/todos/{id}")
    @Operation(description = "할 일을 삭제합니다.")
    public ResponseEntity<?> deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
        return ResponseEntity.ok().build();
    }

}
