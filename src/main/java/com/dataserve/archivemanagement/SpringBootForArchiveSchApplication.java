package com.dataserve.archivemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


// {{JAR-SETTING}}
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringBootForArchiveSchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootForArchiveSchApplication.class, args);
	}

}

//@SpringBootApplication
//@EnableAspectJAutoProxy(exposeProxy = true)
//public class SpringBootForArchiveSchApplication extends SpringBootServletInitializer {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		// Configure the application when deployed as a WAR
//		return application.sources(SpringBootForArchiveSchApplication.class);
//	}
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringBootForArchiveSchApplication.class, args);
//	}
//
//}
