package com.example.LightEpro.common.serviceimpl;

import com.example.LightEpro.common.dto.InterceptorDto000;
import com.example.LightEpro.common.mapper.InterceptorMapper000;
import com.example.LightEpro.common.service.InterceptorService000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jboss.logging.MDC;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterceptorServiceImpl000 implements InterceptorService000 {

    // interceptorMapper000 선언
    private final InterceptorMapper000 interceptorMapper000;

    // 단일 로그 정보 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void createLogInfo(InterceptorDto000 interceptorDto000) throws Exception {
        // method start log
        log.info("createLogInfo Method Start !!!");
        log.info("createLogInfo Method Request Data : " + interceptorDto000);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(interceptorDto000);
        // 단일 로그 등록 메소드 호출
        createLog(interceptorDto000);

        // method end log
        log.info("createLogInfo Method End !!!");
    }

    // interceptorRqDto000 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(InterceptorDto000 interceptorDto000) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + interceptorDto000);

        // 트랜잭션 아이디 할당
        interceptorDto000.setTransactionId(String.valueOf(MDC.get("transactionId")));
        // API 경로값 할당
        interceptorDto000.setApiPath(interceptorDto000.getRequest().getRequestURI());

        // method end log
        log.info("assignObject Method Result Data : " + interceptorDto000);
        log.info("assignObject Method End !!!");
    }

    // 로그 등록 메소드
    @Override
    public void createLog(InterceptorDto000 interceptorDto000) throws Exception {
        // method start log
        log.info("createLogInfo Method Start !!!");
        log.info("createLogInfo Method Request Data : " + interceptorDto000);

        // 로그 등록 Mapper 호출
        interceptorMapper000.insertLog(interceptorDto000);

        // method end log
        log.info("createLogInfo Method End !!!");
    }
}
