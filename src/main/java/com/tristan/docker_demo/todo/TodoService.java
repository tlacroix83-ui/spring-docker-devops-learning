package com.tristan.docker_demo.todo;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.tristan.docker_demo.exception.TodoNotFoundException;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
    }

    public Collection<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public void deleteTodoById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }

}
