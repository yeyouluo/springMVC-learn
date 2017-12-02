package com.yeyouluo.springmvc.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // 使用@RestController，声明是控制器，并且返回数据时不需要@ResponseBody
@RequestMapping("/rest")  
public class DemoRestController {

	@RequestMapping(value="/getjson",produces="application/json;charset=UTF-8")  // 返回数据的媒体类型为json。访问路径http://localhost:8080/springmvc-learn/rest/getjson?id=1&name=yeyouluo
	public DemoObj getJson(DemoObj obj) {
		return new DemoObj( obj.getId()+1, obj.getName()+"xy" );  // 直接返回对象，对象会自动转换成json
	}
	
	@RequestMapping(value="/getxml",produces="application/xml;charset=UTF-8")  // 返回数据的媒体类型为xml。访问路径http://localhost:8080/springmvc-learn/rest/getxml?id=1&name=yeyouluo
	public DemoObj getXml(DemoObj obj) {
		return new DemoObj( obj.getId()+1, obj.getName()+"xy" );  // 直接返回对象，对象会自动转换成xml
	}
}
