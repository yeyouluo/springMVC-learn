package com.yeyouluo.springmvc.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  // 导入静态方法
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yeyouluo.springmvc.MyMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {MyMvcConfig.class})
@WebAppConfiguration("/src/main/resources")  // @WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。它的属性指向的是WEB资源的位置，默认为src/main/webapp，这里改为/src/main/resources
public class TestControllerIntegrationTests {
	private MockMvc mockMvc;  // mockMvc是模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化
	
	@Autowired
	private DemoService demoService;  // 注入Spring的Bean
	
	@Autowired
	WebApplicationContext wac;  
	
	@Autowired
	MockHttpSession session;  // 未使用
	
	@Autowired
	MockHttpServletRequest request;  // 未使用
	
	@Before
	public void setup() {  // 初始化
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testNormalController() throws Exception {
		mockMvc.perform(get("/normal"))  // 模拟向/normal进行get请求
			.andExpect(status().isOk())  // 预期控制返回状态是200
			.andExpect(view().name("page"))  
			.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))  // 预期页面转向真正的/WEB-INF/classes/views/page.jsp
			.andExpect(model().attribute("msg", demoService.saySomething()));  // 预期model里面的值是demoService.saySomething()返回值hello
	}
	
	@Test
	public void testRestController() throws Exception {
		mockMvc.perform(get("/testRest"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/plain;charset=UTF-8")) // 预期返回值的媒体类型为text/plain;charset=UTF-8
			.andExpect(content().string(demoService.saySomething())); // 预期model里面的值是demoService.saySomething()返回值hello
	}
}
