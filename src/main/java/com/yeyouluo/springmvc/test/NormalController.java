package com.yeyouluo.springmvc.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NormalController {

	@Autowired
	DemoService demoService;
	
	@RequestMapping("/normal")
	public String testpage(Model model) {
		model.addAttribute("msg", demoService.saySomething());
		return "page";
	}
	
}