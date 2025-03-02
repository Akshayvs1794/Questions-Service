package com.akshay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsQuestionsServiceApplication
{
	public static void main(String[] args){
		SpringApplication.run(MsQuestionsServiceApplication.class, args);
	}
}