package com.huawei.kunpengimsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.huawei.kunpengimsystem.mapper"})
@SpringBootApplication
public class KunpengImSystemApplication {

	static{
		System.load("/home/libMyJni.so"); 
	}
	
	public static void main(String[] args) { SpringApplication.run(KunpengImSystemApplication.class, args); }

}