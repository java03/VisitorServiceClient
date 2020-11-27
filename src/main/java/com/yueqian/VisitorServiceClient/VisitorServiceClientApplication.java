package com.yueqian.VisitorServiceClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.yueqian.VisitorServiceClient.feignClientService")
public class VisitorServiceClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorServiceClientApplication.class, args);
	}
    @Bean
    public RestTemplate getTemplate() {
    	return new RestTemplate();
    }
}
