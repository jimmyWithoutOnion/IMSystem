package com.huawei.kunpengimsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.nio.file.NoSuchFileException;

@MapperScan(basePackages = {"com.huawei.kunpengimsystem.mapper"})
@SpringBootApplication
public class KunpengImSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(KunpengImSystemApplication.class, args);

		try {
			System.load("/usr/local/mylib/libMyJni.so");
		} catch (Exception e) {
			System.out.print("file not found");
			e.printStackTrace();
		}
	}

}