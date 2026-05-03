package com.tristan.docker_demo.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateAndGetTodo() throws Exception {
        // Test POST
        String todoJson = "{\"title\": \"JUnit test Todo\"}";
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoJson))
                .andExpect(status().isCreated());


        // Get the created todo
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("JUnit test Todo"));
    }

    // Test validation: title should not be blank -> Expect BadRequest (400)
    @Test
    public void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {
        String todoJson = "{\"title\": \"\"}";
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("title must not be blank"))
                .andExpect(jsonPath("$.path").value("/todos"));
    }

    // Test getting a non-existing todo -> Expect NotFound (404)
    @Test
    public void shouldReturnNotFoundForNonExistingTodo() throws Exception {
        mockMvc.perform(get("/todos/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("Todo with id 999 not found"))
                .andExpect(jsonPath("$.path").value("/todos/999"));
    }

}
