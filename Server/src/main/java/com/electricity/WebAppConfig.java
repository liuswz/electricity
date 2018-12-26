package com.electricity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.electricity.interceptor.BaseInterceptor;


@SuppressWarnings("deprecation")
@SpringBootConfiguration
public class WebAppConfig extends WebMvcConfigurerAdapter  {

	@Autowired
	private BaseInterceptor bi;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(bi).addPathPatterns("/*");
	}

}
