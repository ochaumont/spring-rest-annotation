package org.demo.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.demo.todolist.web.rest.httpconverter")
@EnableWebMvc
//@ImportResource({"classpath:META-INF/spring/webContext_httpConverter.xml"})
public class DispatcherServletConfig_HTTPConverter  {

   
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/views/");
		view.setSuffix(".jsp");
		return view;
	}
	
}

