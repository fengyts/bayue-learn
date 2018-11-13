package ng.bayue.test.controller;

import java.lang.annotation.Annotation;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 此拦截器不能拦截@ResponseBody 注解的返回值，若要拦截@ResponseBody注解的返回值有两种方法： 
 * 1.不使用@ResponseBody，在Controller中方法返回值为void, 然后使用response.getWriter.writer()返回；
 * 2. 使用spring4.1以上版本的新特性：@ControllerAdvice注解和spring 4.1以上版本的ResponseBodyAdvice接口 可以获取@ResponseBody返回值
 * 
 * 注意使用@ControllerAdvice时，一定要在context:component-scan配置中配置@ControllerAdvice，并让容器自动扫描加载
 * 
 * @author lenovopc
 *
 */
@ControllerAdvice
public class ResultInterceptor implements ResponseBodyAdvice<Object> {

	/** (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice#supports(org.springframework.core.MethodParameter, java.lang.Class)
	 * 
	 * 只有supports()方法返回true时beforeBodyWrite()方法才会被调用
	 * 
	 */
	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		Annotation[] methodAnnotations = returnType.getMethodAnnotations();
		if (methodAnnotations.length > 0) {
			for (Annotation a : methodAnnotations) {
				if (a instanceof ResponseBody) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		System.out.println("进入handler了。。。");
		if (body instanceof String) {
			return "hahah " + body.toString();
		}
		return body;
	}

}
