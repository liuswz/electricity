package com.electricity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
@EnableCaching
public class ElectricityApplication  extends SpringBootServletInitializer{


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(ElectricityApplication.class);

	}
/*	@RequestMapping(value = {"/",""})
	public String helloboot(){

	    return "login" ;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(ElectricityApplication.class, args);
	}
	
	

}
