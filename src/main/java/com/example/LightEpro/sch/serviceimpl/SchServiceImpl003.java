package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.dto.sch003.SchRsDto003;
import com.example.LightEpro.sch.mapper.SchMapper003;
import com.example.LightEpro.sch.service.SchService003;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl003 implements SchService003 {
    private final SchMapper003 schMapper003;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto003 deleteSingleSch(SchRqDto003 schRqDto003) throws Exception{
        log.info("deleteSingleSch Method Start !!!");
        log.info("deleteSingleSch Method Request Data : " + schRqDto003);
        // TODO 하기 매퍼 메소드 , 서비스 메소드로 분리 필요
        int updateSchDelStCnt = schMapper003.updateSchDelStatus(schRqDto003);
        int updateSchUserDelStCnt = schMapper003.updateSchUsersDelStatus(schRqDto003);

        SchRsDto003 schRsDto003 = SchRsDto003.builder()
                .schmSeq(schRqDto003.getSch().getSchmSeq())
                .schSeq(schRqDto003.getSch().getSchmSeq())
                .updateSchDelStCnt(updateSchDelStCnt)
                .updateSchUserDelStCnt(updateSchUserDelStCnt)
                .build();

        log.info("deleteSingleSch Method Return Data : " + schRsDto003);
        log.info("deleteSingleSch Method End !!!");
        // return
        return schRsDto003;
    }
}
