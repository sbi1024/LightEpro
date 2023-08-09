package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch008.SchRqDto008;
import com.example.LightEpro.sch.dto.sch008.SchRsDto008;
import com.example.LightEpro.sch.mapper.SchMapper008;
import com.example.LightEpro.sch.service.SchService008;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl008 implements SchService008 {
    private final SchMapper008 schMapper008;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto008 removeSingleCal(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeSingleCal Method Start !!!");
        log.info("removeSingleCal Method Request Data : " + schRqDto008);

        // 캘린더 시퀀스 추출
        int calSeq = schRqDto008.getCalendar().getCalSeq();
        // 캘린더 정보를 삭제한다.
        int removeCalDetailInfoCnt = removeCalDetailInfo(schRqDto008);
        // 캘린더 구성원 들을 삭제한다.
        int removeCalUsersCnt = removeCalUsers(schRqDto008);

        // schRsDto008 객체 builder 패턴을 통해 객체 생성
        SchRsDto008 schRsDto008 = SchRsDto008.builder()
                .calSeq(calSeq)
                .removeCalDetailInfoCnt(removeCalDetailInfoCnt)
                .removeCalUsersCnt(removeCalUsersCnt)
                .build();

        // method end log
        log.info("removeSingleCal Method Return Data : " + schRsDto008);
        log.info("removeSingleCal Method End !!!");

        // return
        return schRsDto008;
    }

    @Override
    public int removeCalDetailInfo(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeCalDetailInfo Method Start !!!");
        log.info("removeCalDetailInfo Method Request Data : " + schRqDto008);

        int updateCalDetailInfoCnt = schMapper008.updateCalDetailInfo(schRqDto008);

        // method end log
        log.info("removeCalDetailInfo Method Start !!!");
        log.info("removeCalDetailInfo Method Request Data : " + updateCalDetailInfoCnt);

        // return
        return updateCalDetailInfoCnt;
    }

    @Override
    public int removeCalUsers(SchRqDto008 schRqDto008) throws Exception {
        // method start log
        log.info("removeCalUsers Method Start !!!");
        log.info("removeCalUsers Method Request Data : " + schRqDto008);

        int updateCalUsersCnt = schMapper008.updateCalUsers(schRqDto008);

        // method end log
        log.info("removeCalUsers Method Start !!!");
        log.info("removeCalUsers Method Request Data : " + updateCalUsersCnt);

        // return
        return updateCalUsersCnt;
    }
}
