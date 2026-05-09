package com.tristan.docker_demo.todo;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tristan.docker_demo.exception.TodoNotFoundException;

@Service
public class TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[createTodo] Creating todo: {}", todo);
        }
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getTodoById] Retrieving todo with ID: {}", id);
        }
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Collection<Todo> getAllTodos() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[getAllTodos] Retrieving all todos");
        }
        return todoRepository.findAll();
    }

    public void deleteTodoById(Long id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("[deleteTodoById] Deleting todo with ID: {}", id);
        }
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }

}
