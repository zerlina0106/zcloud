package com.zlp.zerlina_cloud.interceptor;

import com.zlp.zerlina_cloud.comm.Const;
import com.zlp.zerlina_cloud.domain.UsersEntity;
import com.zlp.zerlina_cloud.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.interceptor.Interceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HandlerInterceptor是SpringWebMVC的拦截器，类似于Servlet开发中的过滤器Filter，
 * 1、权限检查：如检测请求是否具有登录权限，如果没有直接返回到登陆页面。
 * 2、性能监控：用请求处理前和请求处理后的时间差计算整个请求响应完成所消耗的时间。
 * 3、日志记录：可以记录请求信息的日志，以便进行信息监控、信息统计等。
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	UserRepository userRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//设置一个bool值用于返回
		Boolean flag = false;
		//获取cookie判断是否有用户已经存在,如果存在就放在session里面
		UsersEntity user;
		Cookie[] cookies = request.getCookies();
		if (cookies != null){
			for(Cookie cookie : cookies){
				if (cookie.getName().equals(Const.LOGIN_SESSION_KEY)){
					System.out.println(cookie.getName()+"-"+cookie.getValue());
					user = userRepository.findByUid(cookie.getValue());
					if (user!=null){
						flag = true;
						request.getSession().setAttribute("user", user);
					}
				}
			}
		}

		//如果访问的是一些不用判断user是否存在的网站
		String uri = request.getRequestURI();
		if (uri.matches("(/index|/error|/register|/login)")
				|| request.getRequestURI().matches("/(assets|fonts|css|images|js)/.*")){
			flag = true;
		}
		// 只有返回true才会继续向下执行，返回false取消当前请求
		return true;
	}

}
