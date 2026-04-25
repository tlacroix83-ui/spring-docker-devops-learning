package com.tristan.docker_demo.todo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoRepository repository;
    
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Todo create(@RequestBody TodoCreateDto todo) {
        return repository.save(new Todo(todo.getTitle()));
    }
    
    @GetMapping
    public List<TodoResponseDto> getAll() {
        return repository.findAll().stream()
                .map(todo -> new TodoResponseDto(todo.getId(), todo.getTitle()))
                .toList();
    }
    
}    