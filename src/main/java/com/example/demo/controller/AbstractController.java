package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

public class AbstractController extends org.springframework.web.servlet.mvc.AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setHeader("Allow", getAllowHeader());
            return null;
        }
        // Delegate to WebContentGenerator for checking and preparing.
        checkRequest(request);
        prepareResponse(response);
        // Execute handleRequestInternal in synchronized block if required.
        if (isSynchronizeOnSession()) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Object mutex = WebUtils.getSessionMutex(session);
                synchronized (mutex) {
                    return handleRequestInternal(request, response);
                }
            }
        }
        return handleRequestInternal(request, response);
    }
}
