package com.zlp.zerlina_cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ZerlinaCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZerlinaCloudApplication.class, args);
	}

}
