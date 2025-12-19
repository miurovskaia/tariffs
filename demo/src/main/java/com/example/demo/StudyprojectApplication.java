package com.example.demo;

import com.example.demo.auth.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.TimeZone;

@SpringBootApplication
public class StudyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyprojectApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter(restTemplate()));
        registrationBean.addUrlPatterns("/tariff/*");
        return registrationBean;
    }

}
