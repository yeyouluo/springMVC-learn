package com.yeyouluo.springmvc;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.yeyouluo.springmvc.advance.messageconverter.MyMessageConverter;
import com.yeyouluo.springmvc.interceptor.DemoInterceptor;

/**
 * mvc配置类
 * 在包com.yeyouluo.springmvc.hello和com.yeyouluo.springmvc.domain都不需要继承WebMvcConfigurerAdapter
 * 
 * @author yeyouluo
 *
 */
@Configuration
@EnableWebMvc // 开启springMVC支持，若无此注解，则重写WebMvcConfigurerAdapter方法无效
@EnableScheduling
@ComponentScan("com.yeyouluo.springmvc")
public class MyMvcConfig extends WebMvcConfigurerAdapter { // 继承WebMvcConfigurerAdapter，重写其方法可对SpringMVC进行配置

	/**
	 * viewResolver会使用model、response、request对象，并将渲染的视图（html/json/xml/pdf...）返回给浏览器
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResovler() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	/**
	 * 静态资源处理
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// addResourceHandler指的是文件放置的目录，addResourceLocations指的是对外暴露的访问路径
		registry.addResourceHandler("/asserts/**").addResourceLocations("classpath:/asserts/");
	}

	@Bean // 配置拦截器bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}

	// 重写addInterceptors，注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
	}

	// 快捷的ViewController，可以代替helloController这样 无任何业务处理，只是简单的页面转向的Controller。
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/fileupload"); // /toUpload --> fileupload.jsp --提交--> /upload
		registry.addViewController("/converter").setViewName("/converter");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}

	// 路匹配参数配置，可以将URL中“.”后面的值不被忽略
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer.setUseSuffixPatternMatch(false);
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}

	@Bean
	public MyMessageConverter converter() {
		return new MyMessageConverter();
	}

	/**
	 * 仅添加一个自定义的HttpMessageConverter，不覆盖默认注册的HttpMessageConverter.
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub
		converters.add(converter());
	}

}
