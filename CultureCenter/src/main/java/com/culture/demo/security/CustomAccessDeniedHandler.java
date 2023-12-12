package com.culture.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customAccessDeniedHandler")
@Log4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("> Access Denied Handler");
        log.error("> Exception: " + accessDeniedException.getMessage()); // 예외 메시지 출력

        // 세션 확인
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 세션이 존재하면 CSRF 토큰 검증 실패 시 로그인 페이지로 리다이렉트
        	log.error("> Session : " + session);
        } else {
            // 세션이 없음
            log.error("> Session not found. Redirecting to login page...");
            
        }
    }

}