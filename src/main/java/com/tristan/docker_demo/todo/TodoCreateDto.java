package com.tristan.docker_demo.todo;
/**
 * DTO for creating a new Todo item. This class is used to receive data from the client when creating a new Todo.
 */
public class TodoCreateDto {
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
