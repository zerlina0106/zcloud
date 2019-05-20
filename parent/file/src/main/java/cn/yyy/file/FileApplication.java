package cn.yyy.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringCloudApplication
@MapperScan("cn.yyy.file.mapper")
public class FileApplication{
    static public void main(String[] args){
        SpringApplication.run(FileApplication.class, args);
    }
}
