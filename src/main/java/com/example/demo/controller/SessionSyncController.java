package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.example.demo.util.SessionUtils;

@RestController
@RequestMapping("/sync")
public class SessionSyncController {

    private static final Logger logger = LoggerFactory.getLogger(SessionSyncController.class);

    @GetMapping("/index")
    public String index(HttpSession session) {
        session.setAttribute("foo", null);
        session.setAttribute("bar", null);
        logger.info("session foo={}", session.getAttribute("foo"));
        logger.info("session bar={}", session.getAttribute("bar"));
        return "clear session:" + session.getId();
    }

    @GetMapping(value = "/{param}")
    public String sayHello(@PathVariable String param, HttpServletRequest request) {
        logger.info("param=" + param);

        Object id = WebUtils.getSessionId(request);
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
                SessionUtils.setAttribute(request, "foo", "wake up");
            } else if(param.equals("read")) {
                logger.info("session foo={}", (String) SessionUtils.getAttribute(request, "foo"));
                logger.info("session bar={}", (String) SessionUtils.getAttribute(request, "bar"));
            }else{
                    SessionUtils.setAttribute(request, "bar", "foo bar foo bar");
            }
        }
        logger.info("session foo={}", (String) SessionUtils.getAttribute(request, "foo"));
        logger.info("session bar={}", (String) SessionUtils.getAttribute(request, "bar"));

        return "Hello,World! " + param;
    }
}
