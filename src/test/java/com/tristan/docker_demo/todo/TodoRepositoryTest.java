package com.tristan.docker_demo.todo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class TodoRepositoryTest {
    
    /* Test container for PostgreSQL */
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("password");

    @Autowired
    private TodoRepository todoRepository;
    
    /* Configure dynamic properties for the test database */
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    // Add test methods here to verify the functionality of TodoRepository
    @Test
    public void shouldSaveTodo() {
        // Given
        Todo todo = new Todo();
        todo.setTitle("Test Todo");

        // When
        Todo savedTodo = todoRepository.save(todo);

        // Then
        assertNotNull(savedTodo.getId());
        assertEquals("Test Todo", savedTodo.getTitle());
    }

}
