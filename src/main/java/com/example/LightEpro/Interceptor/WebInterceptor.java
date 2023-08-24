package com.example.LightEpro.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class WebInterceptor implements HandlerInterceptor {

    // * 인터셉터(Interceptor)의 사용 사례
    // 1. 세부적인 보안 및 인증/인가 공통 작업
    // 2. API 호출에 대한 로깅 또는 검사
    // 3. Controller 로 넘겨주는 정보(데이터)의 가공

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===============================================");
        log.info("==================== START ====================");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("==================== END ======================");
        log.info("===============================================");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) throws Exception {
        // 이 시점에서 history table 에 데이터 insert 하여 관리 진행
    }
}
