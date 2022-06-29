package com.ron2ader.issuetracker.auth;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class AuthorizationExtractor {

    private static final String BEARER_TYPE = "Bearer";

    public static String extract(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(HttpHeaders.AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (isBearerType(value)) {
                return extractAuthHeader(value);
            }
        }
        return null;
    }

    private static String extractAuthHeader(String value) {
        String authHeaderValue = value.substring(BEARER_TYPE.length()).trim();
        return (authHeaderValue.contains(","))
                ? authHeaderValue.split(",")[0]
                : authHeaderValue;
    }

    private static boolean isBearerType(String value) {
        return value.toLowerCase().startsWith(BEARER_TYPE.toLowerCase());
    }

}
