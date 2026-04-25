package com.tristan.docker_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Docker!";
    }

    @GetMapping("/health")
    public String health() {
        return "Alive";
    }
    
}
