package com.example.LightEpro.common.serviceimpl;

import com.example.LightEpro.common.dto.InterceptorDto;
import com.example.LightEpro.common.mapper.InterceptorMapper;
import com.example.LightEpro.common.service.InterceptorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jboss.logging.MDC;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterceptorServiceImpl implements InterceptorService {

    // interceptorMapper 선언
    private final InterceptorMapper interceptorMapper;

    // 단일 로그 정보 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void createLogInfo(InterceptorDto interceptorDto) throws Exception {
        // method start log
        log.info("createLogInfo Method Start !!!");
        log.info("createLogInfo Method Request Data : " + interceptorDto);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(interceptorDto);
        // 단일 로그 등록 메소드 호출
        createLog(interceptorDto);

        // method end log
        log.info("createLogInfo Method End !!!");
    }

    // interceptorRqDto000 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(InterceptorDto interceptorDto) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + interceptorDto);

        // 트랜잭션 아이디 할당
        interceptorDto.setTransactionId(String.valueOf(MDC.get("transactionId")));
        // API 경로값 할당
        interceptorDto.setApiPath(interceptorDto.getRequest().getRequestURI());

        // method end log
        log.info("assignObject Method Result Data : " + interceptorDto);
        log.info("assignObject Method End !!!");
    }

    // 로그 등록 메소드
    @Override
    public void createLog(InterceptorDto interceptorDto) throws Exception {
        // method start log
        log.info("createLog Method Start !!!");
        log.info("createLog Method Request Data : " + interceptorDto);

        // 로그 등록 Mapper 호출
        interceptorMapper.insertLog(interceptorDto);

        // method end log
        log.info("createLog Method End !!!");
    }
}
