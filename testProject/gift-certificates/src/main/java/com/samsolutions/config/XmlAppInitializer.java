package com.samsolutions.config;

import com.samsolutions.config.security.SecurityConfig;
import com.samsolutions.config.web.WebMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Implementation of WebApplicationInitializer interface, used in conjunction with web.xml
 * to configure the ServletContext programmatically.
 */
//rename to AppInitializer
public class XmlAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext container) throws ServletException {

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebMvcConfig.class);
        dispatcherContext.register(SecurityConfig.class);

        // Listener for managing the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(dispatcherContext));

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        // Setting container parameters
        container.setInitParameter("defaultHtmlEscape", "true");

        // Register character encoding filter
        FilterRegistration charEncodingFilterReg =
                container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
        charEncodingFilterReg.setInitParameter("encoding", "UTF-8");
        charEncodingFilterReg.setInitParameter("forceEncoding", "true");
        charEncodingFilterReg.addMappingForUrlPatterns(null, false, "/*");
    }

}