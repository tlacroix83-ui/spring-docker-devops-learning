package com.tristan.docker_demo.todo;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);
    
    private final TodoService service;
    
    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponseDto create(@Valid@RequestBody TodoCreateDto todo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[create] BEGIN");
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[create] Request: {}", todo);
        }
        final Todo todoSaved = service.createTodo(new Todo(todo.getTitle()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[create] Saved todo: {}", todoSaved);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[create] END");
        }
        return new TodoResponseDto(todoSaved.getId(), todoSaved.getTitle());
    }
    
    @GetMapping
    public List<TodoResponseDto> getAll() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getAll] BEGIN");
        }
        final Collection<Todo> todos = service.getAllTodos();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[getAll] Found todos: {}", todos);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getAll] END");
        }
        return todos.stream()
                .map(todo -> new TodoResponseDto(todo.getId(), todo.getTitle()))
                .toList();
    }

    @GetMapping("/{id}")
    public TodoResponseDto getById(@PathVariable Long id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getById] BEGIN");
        }
        final Todo todo = service.getTodoById(id);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[getById] Found todo: {}", todo);
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getById] END");
        }
        return new TodoResponseDto(todo.getId(), todo.getTitle());
    }
}    