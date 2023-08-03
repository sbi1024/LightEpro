package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch003.SchRqDto003;
import com.example.LightEpro.sch.dto.sch003.SchRsDto003;
import com.example.LightEpro.sch.mapper.SchMapper003;
import com.example.LightEpro.sch.service.SchService003;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SchServiceImpl003 implements SchService003 {
    private final SchMapper003 schMapper003;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto003 deleteSingleSch(SchRqDto003 schRqDto003) throws Exception{
        // TODO 수정하고자 하는 일정이 존재하는지 먼저 판단하고 , 없다면 Exception 처리 진행
        int updateSchDelStCnt = schMapper003.updateSchDelStatus(schRqDto003);
        // TODO 1_1) 수정하고자 하는 일정의 참여자 정보 중 , 일정의 등록자와 , 참여자의 마스터 권한이 존재 하는가 판단 후 없다면 Exception 처리 진행
        // TODO 1_2 ) 사유 : 잘못된 일정이기 떄문 (하나의 일정은 , 참여자로 일정의 등록자를 무조건 가지고 있으며 , 해당 참여자는 마스터 권한이어야 함)
        int updateSchUserDelStCnt = schMapper003.updateSchUsersDelStatus(schRqDto003);

        SchRsDto003 schRsDto003 = SchRsDto003.builder()
                .schmSeq(schRqDto003.getSch().getSchmSeq())
                .schSeq(schRqDto003.getSch().getSchmSeq())
                .updateSchDelStCnt(updateSchDelStCnt)
                .updateSchUserDelStCnt(updateSchUserDelStCnt)
                .build();

        return schRsDto003;
    }
}
