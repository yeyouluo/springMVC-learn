package com.yeyouluo.springmvc.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // 必须使用@Controller声明这是一个控制器
public class HelloController {

	// 后续关于viewResolver的配置都放在MyMvcConfig类中处理
//	@RequestMapping("/index")  // 利用@RequestMapping配置URL和方法之间的映射。
//	public String hello() {
//		return "index";   // 和MyMvcConfig配置的前缀后缀拼接起来就是/WEB-INF/classes/index.jsp
//	}
}
