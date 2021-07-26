package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.util.WebUtils;

public class SessionUtils {

    /**
     * 指定した属性名で値を取得します。
     *
     * @param request
     * @param attributeName
     * @param <T>
     * @return
     */
    public static <T> T getAttribute(HttpServletRequest request, String attributeName) {
        T ret = null;
        var session = getSession(request);
        var mutex = getMutex(request);
        if (mutex != null) {
            synchronized (mutex) {
                ret = (T) session.getAttribute(attributeName);
            }
        }
        return ret;
    }

    /**
     * 指定した属性名で値を設定します。
     *
     * @param request
     * @param attributeName
     * @param value
     * @return
     */
    public static void setAttribute(HttpServletRequest request, String attributeName, Object value) {
        var session = getSession(request);
        var mutex = getMutex(request);
        if (mutex != null) {
            synchronized (mutex) {
                session.setAttribute(attributeName, value);
            }
        }
    }

    /**
     * mutexを取得します。
     *
     * @param request
     * @return
     */
    public static Object getMutex(HttpServletRequest request) {
        var session = getSession(request);

        if (session != null) {
            var mutex = WebUtils.getSessionMutex(session);
            return mutex;
        }
        return null;
    }

    /**
     * 存在するセッションを取得します。
     *
     * @param request
     * @return
     */
    public static HttpSession getSession(HttpServletRequest request) {
        return request.getSession(false);
    }
}
