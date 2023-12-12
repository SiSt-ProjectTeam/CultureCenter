package com.culture.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customLoginSuccessHandler")
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
									  , HttpServletResponse response
									  , Authentication authentication // 인증받은 사용자의 정보
	) throws IOException, ServletException {
		log.info("> Login Success...");
		
		  // 추가 디버깅 코드: 세션 및 CSRF 토큰 확인
	    HttpSession session = request.getSession(false);
	    log.info("> Session ID: " + (session != null ? session.getId() : "null"));

	    CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	    log.info("> CSRF Token: " + (csrfToken != null ? csrfToken.getToken() : "null"));

	    List<String> roleNames = new ArrayList<String>();
	    authentication.getAuthorities().forEach(
	            auth -> {
	                roleNames.add(auth.getAuthority());
	            });
	    log.warn("> ROLE NAMES : " + roleNames);
		
		if(roleNames.contains("ROLE_ADMIN")) {
			log.info("> Login Success... ROLE_ADMIN");
			response.sendRedirect("/index.do");
			return;
		} else if(roleNames.contains("ROLE_MANAGER")) {
			log.info("> Login Success... ROLE_MANAGER");
			response.sendRedirect("/ROLE_MANAGER");
			return;
		} else if(roleNames.contains("ROLE_USER")) {
			log.info("> Login Success... ROLE_USER");
			response.sendRedirect("/index.do");
			return;
		}
	}

}
