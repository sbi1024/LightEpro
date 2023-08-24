package com.example.LightEpro.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityFilter {
    /**
     * 스프링 시큐리티 사용을 위한 설정
     */
    @Bean
    public BCryptPasswordEncoder bCryptObject() {
        return new BCryptPasswordEncoder();
    }

    // TODO 차후 참고예정 블로그 : https://catsbi.oopy.io/c0a4f395-24b2-44e5-8eeb-275d19e2a536
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Spring Security 가 설정된 후 , POST API 정상 호출 불가 csrf 를 기본적으로 체크하기 때문 = csrf disable
        http
                .csrf().disable()
                .cors().disable();

        return http.build();
    }
}
