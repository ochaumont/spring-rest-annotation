package org.demo.todolist.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;

@Configuration
@ComponentScan("org.demo.todolist.web.rest.viewresolver")
//@ImportResource({"classpath:META-INF/spring/webContext_viewResolver.xml"})
public class DispatcherServletConfig_ViewResolver  {

	@Autowired
	Jaxb2Marshaller jaxb2Marshaller;
	
	@Bean
	public ViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/views/");
		view.setSuffix(".jsp");
		return view;
	}
	
	
	@Bean
	public Jaxb2Marshaller jaxb2Marshaller() {
		
		//XML View
		Jaxb2Marshaller marschaller = new Jaxb2Marshaller();
        String[] packtoScan = {"org.demo.todolist.domain"};
        marschaller.setPackagesToScan(packtoScan);
       	//marschaller.afterPropertiesSet();
		
        return marschaller;
	}
	
	@Bean
	public ViewResolver contentNegotiatingviewResolver() {
		
		//ContentNegotiatingViewResolver
		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setOrder(1);
		
		//set contentNegotiationManager
		Map<String, MediaType> extensionMapping = new HashMap<String, MediaType>();
		extensionMapping.put("json",new MediaType("application","json"));
		extensionMapping.put("xml",new MediaType("application","xml"));
		
		ContentNegotiationManager contentNegotiationManager =
				new ContentNegotiationManager(new PathExtensionContentNegotiationStrategy(extensionMapping));
		viewResolver.setContentNegotiationManager(contentNegotiationManager);
		
		//set defaultViews
		List<View> defaultViews = new ArrayList<View>();
		
			//Json view
			defaultViews.add(new MappingJacksonJsonView());
			
		
	        defaultViews.add(new MarshallingView(jaxb2Marshaller));
		
	    viewResolver.setDefaultViews(defaultViews);
	        
		return viewResolver;		
	}
	
}
