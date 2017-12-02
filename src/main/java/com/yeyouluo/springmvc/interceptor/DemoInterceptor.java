package com.yeyouluo.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * 
 * 
 * 拦截器实现对每一个请求处理前后进行相关的业务处理，类似于Servlet的Filter。
 *    可让普通的Bean实现HandlerInterceptor接口或者继承HandlerInterceptorAdapter方法来实现自定义拦截器。
 * 同时需要重写WebMvcConfigurerAdapter的addInterceptors方法来注册自定义的拦截器
 * @author yeyouluo
 *
 */
public class DemoInterceptor extends HandlerInterceptorAdapter{  // 继承HandlerInterceptorAdapter来实现自定义拦截器
	
	
	// 重写preHandle方法，在请求发生之前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	// 重写postHandle方法，在请求发生之后执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long)request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("本次请求处理的时间为："+ new Long(endTime - startTime) + "ms");
		request.setAttribute("handlingTime", endTime - startTime);
	}

	
}
