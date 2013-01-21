package org.demo.todolist.config;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = {"org.demo.todolist.service","org.demo.todolist.domain"})
@ImportResource({"classpath:META-INF/spring/applicationContext.xml"})
public class ApplicationConfiguration {

    private final Log log = LogFactory.getLog(ApplicationConfiguration.class);

    @PostConstruct
    public void init() throws IOException {      
    }
}
