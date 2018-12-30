package com.samsolutions.config.app;

import com.samsolutions.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.web.FilterChainProxy;

@Configuration
@ComponentScan(basePackages = { "com.samsolutions" })
@Import({OrmConfig.class})
public class AppConfig {

    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocation(new ClassPathResource("application.properties"));
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }

    @Bean
    public UserDetailsServiceImpl getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public FilterChainProxy getFilterChainProxy() {
        return new FilterChainProxy();
    }


}
