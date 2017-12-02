package com.yeyouluo.springmvc.advance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 控制器
 * @author yeyouluo
 *
 */
@Controller
public class AysncController {
	
	@Autowired
	PushService pushService;  // 定时更新PushService
	
	@RequestMapping("/defer")
	@ResponseBody
	public DeferredResult<String> deferredCall() {  // 返回给客户端DeferredResult
		return pushService.getAsyncUpdate();
	}
}
