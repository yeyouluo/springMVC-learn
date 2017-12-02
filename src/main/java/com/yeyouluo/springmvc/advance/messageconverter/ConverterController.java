package com.yeyouluo.springmvc.advance.messageconverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeyouluo.springmvc.domain.DemoObj;

@Controller
public class ConverterController {

	@RequestMapping(value="/convert", produces= {"application/x-wisely"})  // 指定返回的媒体类型为我们自定义的类型application/x-wisely
	public @ResponseBody DemoObj  convert(@RequestBody DemoObj demoObj) {
		return demoObj;
	}
}
