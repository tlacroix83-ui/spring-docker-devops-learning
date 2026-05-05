package com.tristan.docker_demo.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void shouldCreateTodo() {
        // Given
        Todo todo = new Todo("Test Todo");
        todo.setId(1L);

        when(todoRepository.save(org.mockito.ArgumentMatchers.any(Todo.class))).thenReturn(todo);

        // When
        Todo createdTodo = todoService.createTodo(todo);

        // Then
        assertNotNull(createdTodo);
        assertEquals("Test Todo", createdTodo.getTitle());

        // Capture the argument passed to save() and verify its properties
        ArgumentCaptor<Todo> todoCaptor = ArgumentCaptor.forClass(Todo.class);
        verify(todoRepository, times(1)).save(todoCaptor.capture());
        Todo capturedTodo = todoCaptor.getValue();
        assertEquals("Test Todo", capturedTodo.getTitle());
    }

    @Test
    void shouldGetTodoById() {
        // Given
        Todo todo = new Todo("Existing Todo");
        todo.setId(1L);

        when(todoRepository.findById(1L)).thenReturn(java.util.Optional.of(todo));

        // When
        Todo foundTodo = todoService.getTodoById(1L);

        // Then
        assertNotNull(foundTodo);
        assertEquals("Existing Todo", foundTodo.getTitle());

        verify(todoRepository).findById(1L);
    }

}
