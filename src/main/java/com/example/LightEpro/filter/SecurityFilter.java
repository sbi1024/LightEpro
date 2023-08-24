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
    // * 필터(Filter)의 사용 사례
    // 1. 보안 및 인증/인가 관련 작업
    // 2. 모든 요청에 대한 로깅 또는 검사
    // 3. 이미지/데이터 압축 및 문자열 인코딩
    // 4. Spring 과 분리되어야 하는 기능
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Spring Security 가 설정된 후 , POST API 정상 호출 불가 csrf 를 기본적으로 체크하기 때문 = csrf disable
        http
                .csrf().disable()
                .cors().disable();

        return http.build();
    }
}
