package cn.yyy.user.service;


import cn.yyy.user.mapper.UserMap;
import cn.yyy.user.model.*;
import cn.yyy.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService {

    @Autowired
    public UserMap userMap;
    // 注册
    public Boolean register(User user){
        User newUser = exist(user.getPhone());
        if ((newUser) != null){
            return false;
        } else {
            userMap.insertSelective(user);
            return true;
        }
    }
    // 登录
    public User login(User user){
        if ((user = exist(user)) != null){
            return user;
        } else {
            return null;
        }
    }

    private User exist(User user){
        List<User> users = userMap.select(user);
        if (users.size()>1){
            System.out.println(users);
            throw new RuntimeException();
        }
        if (users.size() == 1){
            return users.get(0);
        }
        return null;
    }
    private User exist(String phone){
        User user = new User();
        user.setPhone(phone);
        return exist(user);
    }
}
