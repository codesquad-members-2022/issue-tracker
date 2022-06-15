package com.team31.codesquad.issuetracker.config.mvc;

import com.team31.codesquad.issuetracker.annotation.LoginId;
import com.team31.codesquad.issuetracker.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LogInIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtUtil jwtUtil;

    @Override public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String authorizationHeader = webRequest.getHeader("Authorization");

        if (authorizationHeader == null) {
            return null;
        }

        String token = authorizationHeader.substring(7);
        String loginId = jwtUtil.getPayload(token);
        System.out.println("loginId = " + loginId);
        return loginId;
    }
}
