package com.yeyouluo.springmvc.domain;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  // 此类为控制器
@RequestMapping("/anno")  // 映射此类访问路径是/anno
public class DemoAnnoController {

	@RequestMapping(produces = "text/plain;charset=UTF-8")  // 此方法使用类级别路径/anno。produces可定制返回的response媒体类型和字符集
	public @ResponseBody String index(HttpServletRequest request) { // 这里也可以接受HttpServletResponse作为参数
		return "url:" + request.getRequestURL() + " can access"; 
	}
	
	@RequestMapping(value="/pathvar/{str}", produces = "text/plain;charset=UTF-8")  // 接受路径参数，访问路径为/anno/pathvar/xxx
	public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access,str: " + str;
	}
	
	@RequestMapping(value="/requestParam", produces = "text/plain;charset=UTF-8") // 演示常规request参数，访问路径为：/anno/requestParam?id=1
	public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
		return "url:" +  request.getRequestURL() + " can access, id : " + id;
	}
	
	@RequestMapping(value="/obj", produces = "text/plain;charset=UTF-8")  // 演示解释参数到对象。访问路径为/anno/obj?id=1&name=xxx
	@ResponseBody  // ResponseBody也可以用在方法上
	public String passObj(DemoObj obj, HttpServletRequest request) {
		return "url:" +  request.getRequestURL() + " can access, obj id : " + obj.getId() + ", obj name:" + obj.getName();
	}
	
	@RequestMapping(value= {"/name1","/name2"}, produces = "text/plain;charset=UTF-8") // 演示映射不同路径到不同方法，访问路径为/anno/name1或/anno/name2
	public @ResponseBody String remove(HttpServletRequest request) {
		return "url:" + request.getRequestURL() + " can access"; 
	}
}
