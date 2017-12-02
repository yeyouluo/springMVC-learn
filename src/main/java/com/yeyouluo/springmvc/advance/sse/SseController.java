package com.yeyouluo.springmvc.advance.sse;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 服务器端推送技术：
 * - SSE，server send event，服务端发送事件，需要新式浏览器的支持
 * - Servlet3.0+ 的异步方法特性，跨浏览器
 * - websocket：双向通信技术
 * @author yeyouluo
 *
 */
@Controller
public class SseController {
	
	@RequestMapping(value="/push", produces="text/event-stream; charset=utf-8")  // 输出类型为text/event-stream，这是服务器端SSE的支持，本例演示5秒想浏览器推送随机消息
	public @ResponseBody String push() {
		Random r = new Random();
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
	}
}
