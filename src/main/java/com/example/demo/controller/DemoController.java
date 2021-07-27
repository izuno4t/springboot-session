package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/demo1")
    public String demo(HttpSession session){
        return getString(session);
    }

    @GetMapping("/demo2")
    public String demo2(HttpSession session){
        return getString(session);
    }


    private String getString(HttpSession session) {
        var value = (Integer)session.getAttribute("a");
        logger.debug("value={}", value);

        if(null == value){
            session.setAttribute("a", 0);
        }else{
            value++;
            session.setAttribute("a",value);
        }

        return  String.format("Hello %s", value);
    }
}
