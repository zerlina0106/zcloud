package com.zlp.zerlina_cloud.controller;

import com.zlp.zerlina_cloud.comm.Const;
import com.zlp.zerlina_cloud.domain.UsersEntity;
import com.zlp.zerlina_cloud.domain.result.ExceptionMsg;
import com.zlp.zerlina_cloud.domain.result.ResponseResult;
import com.zlp.zerlina_cloud.utils.Des3EncryptionUtil;
import com.zlp.zerlina_cloud.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 1、 初始化日志对象 获取 response request
 *       从request中获取session
 * 2、 获取用户，从session中
 * 3、 通过获取到的用户get用户的一些属性
 * 4、
 */
@Controller
public class BaseController {
	/*
	使用指定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类
		如：Logger logger = LoggerFactory.getLogger(com.Book.class);
           logger.debug("日志信息");
		将会打印出: com.Book  : 日志信息
	 */
    protected Logger logger =  LoggerFactory.getLogger(this.getClass());
    
    protected ResponseResult result(ExceptionMsg msg){
    	return new ResponseResult(msg);
    }
    protected ResponseResult result(){
    	return new ResponseResult();
    }
    
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    protected HttpSession getSession() {
        return getRequest().getSession();
    }
    
    protected UsersEntity getUser() {
        return (UsersEntity) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
    }
    
    protected String getUserId() {
    	String id=null;
    	UsersEntity user=getUser();
    	if(user!=null){
    		id=user.getUid();
    	}
        return id;
    }
    
    protected String getUserName() {
    	String userName="defaultUserName";
    	UsersEntity user=getUser();
    	if(user!=null){
    		userName=user.getUsername();
    	}
        return userName;
    }
    
    protected String getUserIp() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }
    
    protected String getPwd(String password){
    	try {
    		String pwd = MD5Util.encrypt(password+Const.PASSWORD_KEY);
    		return pwd;
		} catch (Exception e) {
			logger.error("密码加密异常：",e);
		}
    	return null;
    }

    protected String cookieSign(String value){
        try{
            value = value + Const.PASSWORD_KEY;
            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
            return sign;
        }catch (Exception e){
            logger.error("cookie签名异常：",e);
        }
        return null;
    }
}
