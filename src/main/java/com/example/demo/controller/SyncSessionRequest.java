package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncSessionRequest {

    @RequestMapping(value = "/{param}")
    public String sayHello(@PathVariable String param,  HttpSession session) {
        System.out.println("param=" + param);

        Object id = session.getId();
        if (id == null) {
            System.out.println("session not exist " + id);
            //session.setAttribute("p", p);
        } else {
            System.out.println("session existed ，id=" + id);
            if (param.equals("sleep")) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("wake up!");
            }
        }
        return "Hello,World! " + param;
    }
}
