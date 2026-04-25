package com.tristan.docker_demo.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
    
@Entity
public class Todo {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    public Todo(String title) {
        this.title = title; 
    }
    public Todo() {
        // Default constructor for JPA
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
