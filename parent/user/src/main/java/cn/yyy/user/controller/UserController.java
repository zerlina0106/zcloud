package cn.yyy.user.controller;

import cn.yyy.common.controller.BaseController;
import cn.yyy.common.entity.ResponseData;
import cn.yyy.user.model.User;
import cn.yyy.user.service.TextService;
import cn.yyy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController extends BaseController {

    @Autowired
    TextService textService;

    @Autowired
    UserService userService;

    private final String SUCCESS_REGISTER = "注册成功";
    private final String SUCCESS_LOGIN = "登录成功";
    private final String SUCCESS_SEND = "验证码发送成功";

    private final String ERROR_REGISTER_EXIST = "该手机号已存在";
    private final String ERROR_REGISTER_ERRORVERIFYCODE = "验证码不正确";
    private final String ERROR_LOGIN = "用户名或密码错误";

    @PostMapping("sendMessage")
    public ResponseData sendMessage(@RequestBody User user){
        System.out.println("发送验证码:"+user.getPhone());
        textService.sendMessage(user.getPhone());
        return getTableDataWithMessage(CODE_SUCCESS, SUCCESS_SEND);
    }

    @PostMapping("register")
    public ResponseData register(@RequestBody User user){
        if (textService.checkMessage(user.getPhone(), user.getVerifyCode()) == false){
            return getTableDataWithMessage(CODE_FAIL, ERROR_REGISTER_ERRORVERIFYCODE);
        }
        if (userService.register(user) == false){
            return getTableDataWithMessage(CODE_FAIL, ERROR_REGISTER_EXIST);
        }
        return getTableDataWithMessage(CODE_SUCCESS, SUCCESS_REGISTER);
    }

    @PostMapping("sign")
    public ResponseData sign(@RequestBody User user){
        User newUser = userService.login(user);
        if (newUser != null){
            newUser.setPassword(null);
            return getTableData(newUser);
        } else {
            return getTableDataWithMessage(CODE_FAIL, ERROR_LOGIN);
        }
    }
}
