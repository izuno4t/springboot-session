package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sync")
public class SessionSyncController {

    private static final Logger logger = LoggerFactory.getLogger(SessionSyncController.class);

    @GetMapping("/index")
    public String index(HttpSession session){
        session.setAttribute("foo", null);
        session.setAttribute("bar", null);

        logger.info("session foo={}", session.getAttribute("foo"));
        logger.info("session bar={}", session.getAttribute("bar"));
        return "clear session:" + session.getId();
    }

    @GetMapping(value = "/{param}")
    public String sayHello(@PathVariable String param, HttpSession session) {
        logger.info("param=" + param);

        Object id = session.getId();
        if (id == null) {
            logger.info("session not exist " + id);
            //session.setAttribute("p", p);
        } else {
            logger.info("session existed ，id=" + id);
            if (param.equals("sleep")) {
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                logger.info("wake up!");
                session.setAttribute("foo", "wake up");
            }else{
                session.setAttribute("bar", "foo bar foo bar");
            }
        }
        logger.info("session foo={}", session.getAttribute("foo"));
        logger.info("session bar={}", session.getAttribute("bar"));

        return "Hello,World! " + param;
    }
}
