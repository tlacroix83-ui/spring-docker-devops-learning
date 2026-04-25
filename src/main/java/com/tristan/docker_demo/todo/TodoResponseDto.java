package com.tristan.docker_demo.todo;

public class TodoResponseDto {
    private Long id;
    private String title;

    public TodoResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
