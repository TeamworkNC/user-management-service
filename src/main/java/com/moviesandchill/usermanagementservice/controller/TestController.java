package com.moviesandchill.usermanagementservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping
    private String hello() {
        return "hello world!";
    }
}
