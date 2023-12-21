package com.culture.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.culture.demo.mapper.CartMapper;
import com.culture.demo.security.CustomerUser;

public class CartNumInterceptor implements HandlerInterceptor {

	@Autowired
	private ApplicationContext context; // application-scan된 bean객체 접근하기 위한 context
	 
	@Override  //controller -> dispatcher(view생성 이전)
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(">인터셉터 ... CartNumInterceptor.postHandle() ...");
		Authentication authentication = null;
		int member_sq = -1;
		int totCartCnt = 0;
		try {
			authentication = SecurityContextHolder.getContext().getAuthentication();
			CustomerUser principal = (CustomerUser) authentication.getPrincipal();
			member_sq = principal.getMember_sq();
		} catch (Exception e) {
			System.out.println("로그인 안된 상태"); // 
		}
		// 로그인 한 경우 totCartCnt 가져오기
		if (member_sq!= -1) {
			//System.out.println("member_sq : "+member_sq);
			CartMapper cartMapper = context.getBean(CartMapper.class);
			totCartCnt = cartMapper.getTotCartCnt(member_sq);
		}
		// view를 안거치는 경우( @ResponseBody, ... )제외 ( 즉, ajax에서는 소용이 없다 )
		if (modelAndView != null) {
			modelAndView.addObject("totCartCnt", totCartCnt);
	    }
		//System.out.println("totCartCnt : "+totCartCnt);
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

}
