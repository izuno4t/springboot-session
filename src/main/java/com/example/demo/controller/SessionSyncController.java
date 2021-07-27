package com.example.demo.controller;

import com.example.demo.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/sync")
public class SessionSyncController {

    private static final Logger logger = LoggerFactory.getLogger(SessionSyncController.class);

    @GetMapping("/index")
    public String index(HttpSession session) {
        session.setAttribute("sleep", null);
        session.setAttribute("foo", null);
        logger.info("session foo={}", session.getAttribute("sleep"));
        logger.info("session bar={}", session.getAttribute("foo"));
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
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                logger.info("wake up!");
                SessionUtils.setAttribute(request, "sleep", "wake up");
            } else if (param.equals("read")) {
                logger.info("session foo={}", (String) SessionUtils.getAttribute(request, "sleep"));
                logger.info("session bar={}", (String) SessionUtils.getAttribute(request, "foo"));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                logger.info("session foo={}", (String) SessionUtils.getAttribute(request, "sleep"));
                logger.info("session bar={}", (String) SessionUtils.getAttribute(request, "foo"));
            } else {
                SessionUtils.setAttribute(request, "foo", "foo bar foo bar");
            }
        }
        logger.info("session foo={}", (String) SessionUtils.getAttribute(request, "sleep"));
        logger.info("session bar={}", (String) SessionUtils.getAttribute(request, "foo"));

        return "Hello,World! " + param;
    }
}
