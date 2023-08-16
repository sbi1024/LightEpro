package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch009.SchRqDto009;
import com.example.LightEpro.sch.dto.sch009.SchRsDto009;
import com.example.LightEpro.sch.mapper.SchMapper009;
import com.example.LightEpro.sch.service.SchService009;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl009 implements SchService009 {

    private final SchMapper009 schMapper009;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto009 findMyCalList(SchRqDto009 schRqDto009) throws Exception {
        // TODO EMP 모듈 쪽 정보가 처리가 되어야 작업 진행 가능할 것으로 확인됨
        // 쿼리상에 회사 , 부서 , 사원정보를 통한 통합 조회가 필요하기 때문
        return null;
    }
}
