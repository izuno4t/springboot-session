package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
public class ExampleController {

    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @GetMapping("/foo")
    public String foo() {
        return "foo";
    }

    @GetMapping("/bar")
    public String bar() {
        return "foo";
    }

}
