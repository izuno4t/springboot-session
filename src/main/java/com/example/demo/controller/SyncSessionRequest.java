package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SyncSessionRequest {

    @GetMapping("/index")
    public String index(HttpSession session){
        session.setAttribute("p", null);
        session.setAttribute("q", null);
        System.out.println("p="+session.getAttribute("p"));
        System.out.println("q="+session.getAttribute("q"));
        return "index";
    }


    @GetMapping(value = "/{param}")
    public String sayHello(@PathVariable String param,  HttpSession session) {
        System.out.println("param=" + param);

        Object id = session.getId();
        if (id == null) {
            System.out.println("session not exist " + id);
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
                session.setAttribute("q", "q");
            }else{
                session.setAttribute("p", "p");
            }
        }
        System.out.println("p="+session.getAttribute("p"));
        System.out.println("q="+session.getAttribute("q"));

        return "Hello,World! " + param;
    }
}
