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
    public SchRsDto003 removeSingleSch(SchRqDto003 schRqDto003) throws Exception{
        log.info("removeSingleSch Method Start !!!");
        log.info("removeSingleSch Method Request Data : " + schRqDto003);

        int curSeq = schRqDto003.getSch().getSchmSeq();
        int updateSchDelStCnt = updateSchDelStatus(schRqDto003);
        int updateSchUserDelStCnt = updateSchUsersDelStatus(schRqDto003);

        SchRsDto003 schRsDto003 = SchRsDto003.builder()
                .schmSeq(curSeq)
                .schSeq(curSeq)
                .updateSchDelStCnt(updateSchDelStCnt)
                .updateSchUserDelStCnt(updateSchUserDelStCnt)
                .build();

        log.info("removeSingleSch Method Return Data : " + schRsDto003);
        log.info("removeSingleSch Method End !!!");
        // return
        return schRsDto003;
    }

    @Override
    public int updateSchDelStatus(SchRqDto003 schRqDto003) throws Exception {
        log.info("updateSchDelStatus Method Start !!!");
        log.info("updateSchDelStatus Method Request Data : " + schRqDto003);

        int updateSchDelStCnt = schMapper003.updateSchDelStatus(schRqDto003);

        log.info("updateSchDelStatus Method Return Data : " + updateSchDelStCnt);
        log.info("updateSchDelStatus Method End !!!");
        // return
        return updateSchDelStCnt;
    }

    @Override
    public int updateSchUsersDelStatus(SchRqDto003 schRqDto003) throws Exception {
        log.info("updateSchUsersDelStatus Method Start !!!");
        log.info("updateSchUsersDelStatus Method Request Data : " + schRqDto003);

        int updateSchUserDelStCnt = schMapper003.updateSchUsersDelStatus(schRqDto003);

        log.info("updateSchUsersDelStatus Method Return Data : " + updateSchUserDelStCnt);
        log.info("updateSchUsersDelStatus Method End !!!");
        // return
        return updateSchUserDelStCnt;
    }
}
