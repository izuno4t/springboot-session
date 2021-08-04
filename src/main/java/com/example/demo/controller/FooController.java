package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value ="/foo")
public class FooController {

    @GetMapping("/foo")
    public String foo(){
        return "foo";
    }

    @GetMapping("/bar")
    public String bar(HttpSession session) {
        return "bar";
    }

}
