package org.demo.todolist.config;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfigurer implements ServletContextListener {

    private final Log log = LogFactory.getLog(WebConfigurer.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        log.info("Web application configuration");

        log.debug("Configuring Spring root application context");
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfiguration.class);
        rootContext.refresh();

        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootContext);

        log.debug("Configuring Spring Web application context for HttpConverter");
        AnnotationConfigWebApplicationContext dispatcherServletConfig_HTTPConverter = new AnnotationConfigWebApplicationContext();
        dispatcherServletConfig_HTTPConverter.setParent(rootContext);
        dispatcherServletConfig_HTTPConverter.register(DispatcherServletConfig_HTTPConverter.class);
      
        
        log.debug("Configuring Spring Web application context for ViewResolver");
        AnnotationConfigWebApplicationContext dispatcherServletConfig_ViewResolver = new AnnotationConfigWebApplicationContext();
        dispatcherServletConfig_ViewResolver.setParent(rootContext);
        dispatcherServletConfig_ViewResolver.register(DispatcherServletConfig_ViewResolver.class);
    

        log.debug("Registering Spring MVC Servlet for HttpConverter");
        ServletRegistration.Dynamic dispatcherServlet_HTTPConverter = servletContext.addServlet("dispatcher_HTTPConverter", new DispatcherServlet(dispatcherServletConfig_HTTPConverter));        
        dispatcherServlet_HTTPConverter.addMapping("/rest-converter/*");        
        dispatcherServlet_HTTPConverter.setLoadOnStartup(2);
        
        log.debug("Registering Spring MVC Servlet for ViewResolver");
        ServletRegistration.Dynamic dispatcherServlet_ViewResolver = servletContext.addServlet("dispatcher_ViewResolver", new DispatcherServlet(dispatcherServletConfig_ViewResolver));        
        dispatcherServlet_ViewResolver.addMapping("/rest-view/*");        
        dispatcherServlet_ViewResolver.setLoadOnStartup(2);
        
        rootContext.refresh();
        log.debug("Web application fully configured");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Destroying Web application");
        WebApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());
        AnnotationConfigWebApplicationContext gwac = (AnnotationConfigWebApplicationContext) ac;
        gwac.close();
        log.debug("Web application destroyed");
    }

}
