package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class RequestUtils {

    public static final String X_REQUESTED_WITH = "X-Requested-With";

    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * User-Agentを取得します。
     *
     * @param request
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return StringUtils.trimToEmpty(request.getHeader(HttpHeaders.USER_AGENT));
    }

    /**
     * Ajax通信であるかを示す値を返します。
     *
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        var header = request.getHeader(X_REQUESTED_WITH);
        return StringUtils.equalsIgnoreCase(XML_HTTP_REQUEST, header);
    }

    /**
     * HttpServletRequestを返します。
     *
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        var attributes = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes) attributes).getRequest();
    }

    /**
     * サイトURLを返します。
     *
     * @return
     */
    public static String getSiteUrl() {
        var servletRequest = getHttpServletRequest();

        String scheme = servletRequest.getScheme();
        String host = servletRequest.getRemoteHost();
        int port = servletRequest.getServerPort();
        String path = servletRequest.getContextPath();

        String siteUrl;
        if (port == 80 || port == 443) {
            siteUrl = StringUtils.join(scheme, "://", host, path);
        } else {
            siteUrl = StringUtils.join(scheme, "://", host, ":", String.valueOf(port), path);
        }

        return siteUrl;
    }
}
