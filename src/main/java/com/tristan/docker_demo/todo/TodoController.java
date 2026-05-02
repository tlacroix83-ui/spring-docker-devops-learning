package com.tristan.docker_demo.todo;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoRepository repository;
    
    public TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDto create(@Valid@RequestBody TodoCreateDto todo) {
        final Todo todoSaved = repository.save(new Todo(todo.getTitle())); 
        return new TodoResponseDto(todoSaved.getId(), todoSaved.getTitle());
    }
    
    @GetMapping
    public List<TodoResponseDto> getAll() {
        return repository.findAll().stream()
                .map(todo -> new TodoResponseDto(todo.getId(), todo.getTitle()))
                .toList();
    }
    
}    