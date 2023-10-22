package com.dataserve.archivemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringBootForArchiveSchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootForArchiveSchApplication.class, args);
	}

}
