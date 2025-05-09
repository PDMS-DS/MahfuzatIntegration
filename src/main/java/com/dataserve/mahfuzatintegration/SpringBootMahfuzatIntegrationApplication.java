package com.dataserve.mahfuzatintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


// {{JAR-SETTING}}
//@SpringBootApplication
//@EnableAspectJAutoProxy(exposeProxy = true)
//public class SpringBootMahfuzatIntegrationApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringBootMahfuzatIntegrationApplication.class, args);
//	}
//
//}

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SpringBootMahfuzatIntegrationApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		// Configure the application when deployed as a WAR
		return application.sources(SpringBootMahfuzatIntegrationApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(SpringBootMahfuzatIntegrationApplication.class, args);
	}

}
