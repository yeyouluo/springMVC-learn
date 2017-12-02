package com.yeyouluo.springmvc.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice // 声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册为spring的Bean。
public class ExceptionHandlerAdvice {

	@ExceptionHandler(value = Exception.class) // @ExceptionHandler在此定义为全局处理，通过@ExceptionHandler的value属性可过滤拦截的条件。这里我们拦截所有的Exception。
	public ModelAndView exception(Exception exception, WebRequest webRequest) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("errorMsg", exception.getMessage());
		return modelAndView;
	}

	@ModelAttribute // 将键值对添加到全局，所有注解了@RequestMapping的方法都可以获得此键值对
	public void addAtrributes(Model model) {
		model.addAttribute("msg", "额外信息");
	}

	@InitBinder // 用来设置WebDataBinder,WebDataBinder用来自动绑定前台请求参数到Model中。
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id");
	}

}
