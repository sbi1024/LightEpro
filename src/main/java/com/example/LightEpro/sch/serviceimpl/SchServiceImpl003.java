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
        int updateSchDelStCnt = schMapper003.updateSchDelStatus(schRqDto003);
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
