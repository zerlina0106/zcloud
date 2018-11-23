package com.zlp.zerlina_cloud.interceptor;

		import org.springframework.beans.factory.annotation.Autowired;
		import org.springframework.context.annotation.Configuration;
		import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
		import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {
	@Autowired
	LoginInterceptor interceptor;
	/**
	 * 登录session key
	 */
	public final static String SESSION_KEY = "user";

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor).addPathPatterns("/**");
	}
}
