package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl004 implements SchService004 {
    private final SchMapper004 schMapper004;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto004 findSchList(SchRqDto004 schRqDto004) throws Exception {
        log.info("findSchList Method Start !!!");
        log.info("findSchList Method Request Data : " + schRqDto004);

        List<SchRsDto004.SchInfo> schInfos = selectSchList(schRqDto004);
        int schInfoCnt = selectSchListCnt(schRqDto004);

        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .schInfos(schInfos)
                .schInfoCnt(schInfoCnt)
                .build();

        log.info("findSchList Method Return Data : " + schRqDto004);
        log.info("findSchList Method End !!!");
        // return
        return schRsDto004;
    }

    @Override
    public List<SchRsDto004.SchInfo> selectSchList(SchRqDto004 schRqDto004) throws Exception {
        log.info("selectSchList Method Start !!!");
        log.info("selectSchList Method Request Data : " + schRqDto004);

        // 일정 목록 조회 메소드 호출
        List<SchRsDto004.SchInfo> schInfos = schMapper004.selectSchList(schRqDto004);

        log.info("selectSchList Method Return Data : " + schInfos);
        log.info("selectSchList Method End !!!");
        // return
        return schInfos;
    }

    @Override
    public int selectSchListCnt(SchRqDto004 schRqDto004) throws Exception {
        log.info("selectSchListCnt Method Start !!!");
        log.info("selectSchListCnt Method Request Data : " + schRqDto004);

        int schInfoCnt = schMapper004.selectSchListCnt(schRqDto004);

        log.info("selectSchListCnt Method Return Data : " + schInfoCnt);
        log.info("selectSchListCnt Method End !!!");
        // return
        return schInfoCnt;
    }
}
