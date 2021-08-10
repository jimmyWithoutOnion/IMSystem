package com.huawei.kunpengimsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.huawei.kunpengimsystem.mapper"})
@SpringBootApplication
public class KunpengImSystemApplication {

	public static void main(String[] args) { SpringApplication.run(KunpengImSystemApplication.class, args); }

}