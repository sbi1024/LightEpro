package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl004 implements SchService004 {
    private final SchMapper004 schMapper004;

    @Override
    public SchRsDto004 selectSchList(SchRqDto004 schRqDto004) throws Exception {
        log.info("selectSchList Method Start !!!");
        log.info("selectSchList Method Request Data : " + schRqDto004);

        // 일정 목록 조회 메소드 호출
        // 회사/부서/개인 조건 추가 및 , 참여자 테이블 매핑으로 인한 , 데이터 중복 제거 필요
        List<SchRsDto004.SchInfo> schInfos = schMapper004.selectSchList(schRqDto004);

        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .schInfos(schInfos)
                .build();

        log.info("selectSchList Method Return Data : " + schRqDto004);
        log.info("selectSchList Method End !!!");
        // return
        return schRsDto004;
    }
}
