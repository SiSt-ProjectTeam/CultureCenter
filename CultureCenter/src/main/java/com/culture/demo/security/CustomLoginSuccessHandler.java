package com.culture.demo.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customLoginSuccessHandler")
@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		log.warn("> Login Success...");
		List<String> roleNames = new ArrayList<String>();
		
		authentication.getAuthorities().forEach(
				auth -> {
				      roleNames.add(auth.getAuthority());	
				});
		
		log.warn("> ROLE NAMES : " + roleNames );
		
		if ( roleNames.contains("ROLE_USER") ) {
			// 이전 페이지의 URL 얻기
	        String referer = request.getHeader("Referer");

	        // 이전 페이지로 리다이렉트
	        if (referer != null && !referer.isEmpty()) {
	            response.sendRedirect(referer);
	            return;
	        } 
			
		} 
		/* manager 혹은 admin 관련 코드 추가시 사용
		else if ( roleNames.contains("ROLE_MANAGER") ) {
			return;
		} else if ( roleNames.contains("ROLE_ADMIN") ) {
			return;
		}
		*/
		response.sendRedirect("/index.do");
		return;
	}

}
