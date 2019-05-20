package cn.yyy.user;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringCloudApplication
@MapperScan(basePackages = "cn.yyy.user.mapper")
public class UserApplication {
    public static void main(String[] args){
        SpringApplication.run(UserApplication.class, args);
    }
}
