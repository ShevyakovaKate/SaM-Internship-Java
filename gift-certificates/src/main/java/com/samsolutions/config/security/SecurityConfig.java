package com.samsolutions.config.security;

import com.samsolutions.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.samsolutions.config" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/orders/user/**").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/users").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/orders/**").permitAll()
                .antMatchers("/login").permitAll() //TODO сюда юзер не может и админ
                .antMatchers("/registration").permitAll() //TODO -ю-
                .antMatchers("/certificates/**").permitAll() //TODO админ не может входить на эту страницу
                .antMatchers("/user/**").hasAuthority("USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(getUrlAuthorizationSuccessHandler())
                .failureUrl("/error")
                .usernameParameter("login")
                .passwordParameter("password")
                .and().
                logout()
                .logoutUrl("/logout")
                .permitAll().logoutSuccessUrl("/")
                .and()
                .csrf();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Bean
    public UrlAuthorizationSuccessHandler getUrlAuthorizationSuccessHandler(){
        return new UrlAuthorizationSuccessHandler();
    }

}
