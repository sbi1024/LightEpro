package com.example.LightEpro.Interceptor;

import com.example.LightEpro.common.dto.InterceptorDto000;
import com.example.LightEpro.common.service.InterceptorService000;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;
    private final InterceptorService000 interceptorService000;

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
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
            return;
        }

        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

        String requestBody = null;
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0) {
                requestBody = objectMapper.writeValueAsString(objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            }
        }

        String responseBody = null;
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                responseBody = objectMapper.writeValueAsString(objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }

        interceptorService000.createLogInfo(InterceptorDto000.builder().request(request).requestBody(requestBody).responseBody(responseBody).build());
    }
}
