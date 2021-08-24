package com.senob.userapi.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity //기본적인 웹 시큐어 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // http servlet request 접근제한 설정
                .antMatchers("/api/hello").permitAll()
                .anyRequest().authenticated(); // 나머진 모두 인증받아야 함
    }
}
