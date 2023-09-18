package com.example.LightEpro.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;


import javax.servlet.DispatcherType;
import java.util.Collections;

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
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setMaxAge(3600L); //1시간
                    return config;
                })) // cors 커스텀 설정

                .csrf().disable() // csrf 미사용 설정

                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .antMatchers("/css/**", "/image/**", "/js/**", "/", "/login", "/layouts/**", "/fragments/**", "/version", "/health").permitAll() // 인증에 대한 예외를 정의한다 (설정한 URL은 인증을 요구하지 않는다.)
                        .anyRequest().authenticated() // 어떠한 요청이라도 인증을 적용한다.
                )

                .formLogin((formLogin) -> formLogin // 로그인은 form 방식으로만 설정
                        .loginPage("/login") // 로그인 페이지 설정
                        .loginProcessingUrl("/login_proc") // 로그인 프로세스 url (form action 값 지정)
                        .usernameParameter("userName") // 로그인시 아이디 키값 설정
                        .passwordParameter("userPassword") // 로그인시 패스워드 키값 설정
                        .defaultSuccessUrl("/home") // 로그인 성공시 이동 페이지 설정
                        .failureUrl("/login") // 로그인 실패시 이동 페이지 설정
                )

                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login") // 로그아웃 성공시 이동 페이지 설정
                        .invalidateHttpSession(true)) // 로그아웃시 세션 삭제

                .build();
    }
}
