package com.zzxt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zzxt.controller.LicenseController;
/**
 * 操作拦截器
 * @author think
 *
 */
@Component
public class adminLoginInterceptor implements HandlerInterceptor{
	private static Logger logger = Logger.getLogger(adminLoginInterceptor.class.getName());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO
		//判断token是否失效，并且判断cookie中的sessid是否存在(如果失效或者不存在则跳转到登陆页)
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
