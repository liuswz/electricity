package com.electricity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebMvcConfig  implements  WebMvcConfigurer{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/img/**").addResourceLocations("file:H:/picpath/");
		 registry.addResourceHandler("/img/**").addResourceLocations("file:/root/apache-tomcat-8.5.29/img/");
		 registry.addResourceHandler("/img2/**").addResourceLocations("file:/root/apache-tomcat-8.5.29/img2/");
		 registry.addResourceHandler("/apk/**").addResourceLocations("file:/root/apache-tomcat-8.5.29/apk/");
    }


}
