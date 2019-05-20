package com.zlp.zerlina_cloud.controller;

import com.zlp.zerlina_cloud.comm.Const;
import com.zlp.zerlina_cloud.domain.MailContentTypeEnum;
import com.zlp.zerlina_cloud.domain.RegisterEntity;
import com.zlp.zerlina_cloud.domain.UsersEntity;
import com.zlp.zerlina_cloud.interceptor.WebMvcConfg;
import com.zlp.zerlina_cloud.jpa.RegisterRepository;
import com.zlp.zerlina_cloud.jpa.UserRepository;
import com.zlp.zerlina_cloud.utils.MailSenderUtils;
import com.zlp.zerlina_cloud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;


@Controller
public class UserController extends BaseController {
	//@RestController表明这是一个控制器类，@Autowired用来进行自动依赖注入，将刚才定义的仓库注入到控制器中
	@Autowired
	UserRepository userRepository;
	@Autowired
	RegisterRepository registerRepository;

	@RequestMapping(value = "/error",method = RequestMethod.GET)
	public String error(){
		return "error";
	}

	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String register(){
		return "register";
	}

	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String register(RegisterEntity user) throws Exception {
		String username = user.getUsername();
		String email = user.getEmail();
		//设置用户的uid
		String uid = UUID.randomUUID().toString();
		user.setUid(uid);
		if (username!=null){
			//如果数据库中没有相同的用户名就把用户存储到register表中
			if(userRepository.findByUsername(username) == null && registerRepository.findByUsername(username) == null){
				registerRepository.save(user);
				// 邮箱校验
				new MailSenderUtils()
						.title("注册zcloud邮箱验证")
						.content("您好，欢迎您注册zcloud," +
								"请点击如下链接进行验证： ")
						.contentType(MailContentTypeEnum.TEXT)
						.content("http://127.0.0.1:8080/registerSuccess?uid="+user.getUid())
						.contentType(MailContentTypeEnum.HTML)
						.targets(new ArrayList<String >(){{add(email);}}).send();
				return "index";
			}else{
				//用户已经存在了，不可以重复注册
				getSession().setAttribute("error","用户已存在");
				return "error";
			}
		}else {
			//为读取到用户名
			getSession().setAttribute("error","未读取到用户名");
			return "error";
		}
	}

	@RequestMapping(value = "/registerSuccess",method = RequestMethod.GET)
	public String registerSuccess(String uid){
		if (uid == null){
			getSession().setAttribute("error","未读取到uid");
			return "error";
		}
		RegisterEntity register = registerRepository.findByUid(uid);
		if (register == null){
			getSession().setAttribute("error","未读取到用户");
			return "error";
		}
		UsersEntity user = UserUtil.convert(register);
		userRepository.save(user);
		registerRepository.deleteByUid(uid);
		return "registerSuccess";
	}

	//忘记密码后填写用户名，发送重置邮件给用户
	@RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
	public String forgetPassword(UsersEntity usersEntity, HttpServletResponse response) throws Exception {
		//表单中获取用户名
		UsersEntity user = userRepository.findByUsername(usersEntity.getUsername());
		String uid = user.getUid();
		String email = user.getEmail();
		//如果找到了这个用户
		if(user.getUsername()!=null){
			//给用户发邮件
			new MailSenderUtils()
					.title("zcloud找回密码服务")
					.content("您好，请您点击如下链接," +
							"重置您的密码： ")
					.contentType(MailContentTypeEnum.TEXT)
					.content("http://127.0.0.1:8080/resetPassword?uid="+user.getUid())
					.contentType(MailContentTypeEnum.HTML)
					.targets(new ArrayList<String >(){{add(email);}}).send();
			response.sendRedirect("/index");
			return "index";
		}else{
			getSession().setAttribute("error","不存在此用户名");
			return "error";
		}
	}
	@RequestMapping(value = "/forgetPassword",method = RequestMethod.GET)
	public String forgetPassword(){return "forgetPassword";}

	@RequestMapping(value = "/resetPassword",method = RequestMethod.GET)
	public String resetPassword(){return "resetPassword";}

	//重置密码
	@RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
	public String resetPassword(UsersEntity usersEntity, HttpServletResponse response, String uid) throws IOException {
		//表单中获取用户名 重置的密码
		UsersEntity user = userRepository.findByUsername(usersEntity.getUsername());
		if(uid==user.getUid()){
			//如果输入的用户名和要求重置的一样才可以重置
			user.setPassword(usersEntity.getPassword());
			userRepository.save(user);
			getSession().setAttribute("user",usersEntity);
			response.sendRedirect("/login");
			return "login";
		}else {
			//如果输入的用户名和要求重置的不一样就报错
			getSession().setAttribute("error","请输入您自己的用户名");
			return "error";
		}

	}


	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login(){return "login";}

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(UsersEntity user, HttpServletResponse response) throws IOException {
		String username = user.getUsername();
		String password = user.getPassword();
		UsersEntity usersEntity = userRepository.findByUsername(username);

		//如果成功找到用户名密码相同的userentity，那么跳转index
		if(usersEntity == null){
			getSession().setAttribute("error","用户不存在");
			return "error";
		}else if(user.getPassword().equals(usersEntity.getPassword())){
			Cookie c1 = new Cookie(Const.LOGIN_SESSION_KEY,usersEntity.getUid());
			c1.setPath("/");
			response.addCookie(c1);
			response.sendRedirect("/index");
			return "index";
		}else  if(!user.getPassword().equals(usersEntity.getPassword())){
			getSession().setAttribute("error","密码错误");
			return "error";
		}
		return "index";
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Enumeration<String> attributeNames = session.getAttributeNames();
		while (attributeNames.hasMoreElements()){
			String name = attributeNames.nextElement();
			session.removeAttribute(name);
		}
		Cookie cookies[] = request.getCookies();
		for(int i=0;i<cookies.length;i++){
			cookies[i].setMaxAge(0);
			response.addCookie(cookies[i]);
		}

		return "index";
	}

	@RequestMapping(value = "/modifyUser",method = RequestMethod.POST)
	public String modifyUser(UsersEntity usersEntity,HttpServletRequest request, HttpServletResponse response) throws IOException {
		//修改用户名
		String username = usersEntity.getUsername();
		Object o = request.getSession().getAttribute("user");
		if(!(o instanceof UsersEntity)){
			getSession().setAttribute("error","此用户不存在");
			return "error";
		}
		if(username.equals("") || userRepository.findByUsername(username)!=null){
			getSession().setAttribute("error","未读取到用户名");
			return "error";
		}
		String uid = ((UsersEntity) o).getUid();
//		System.out.println("line118"+username);
		UsersEntity old = userRepository.findByUid(uid);
		String password = old.getPassword();
		String email = old.getEmail();
		usersEntity.setUid(uid);
		usersEntity.setPassword(password);
		usersEntity.setEmail(email);
		userRepository.save(usersEntity);
		getSession().setAttribute("user",usersEntity);
		response.sendRedirect("/index");
		return "index";

	}




}
