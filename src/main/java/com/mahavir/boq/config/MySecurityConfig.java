package com.mahavir.boq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import com.mahavir.boq.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@Component
public class MySecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    public CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/product",
                "/v3/api-docs",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/swagger-ui/index.html",
                "/webjars/**").permitAll() //one doubt of image returning
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
                

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailService);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // bcrypt encoding algorithm
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    
    // @Bean
    // public JavaMailSender javaMailSender() { 
    //       return new JavaMailSenderImpl();
    // }
    

    // @Primary
    // @Bean
    // public FreeMarkerConfigurationFactoryBean factoryBean(){
    //     FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
    //     bean.setTemplateLoaderPath("classpath:/templates");
    //     return bean;
    // }


    @Primary
    @Bean
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration() {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("classpath:templates/");
        return bean;
    }
}