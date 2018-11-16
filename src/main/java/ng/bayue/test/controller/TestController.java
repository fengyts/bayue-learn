package ng.bayue.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {

	/**
	 * 测试spring @Reponsebody 返回值处理拦截器
	 * @return
	 */
	@RequestMapping("testResultInterceptor")
	@ResponseBody
	public String test() {
		return "hello world!";
	}

}
