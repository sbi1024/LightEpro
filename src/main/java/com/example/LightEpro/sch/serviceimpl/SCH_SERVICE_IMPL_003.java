package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_002.SCH_RQ_DTO_002;
import com.example.LightEpro.sch.dto.sch_002.SCH_RS_DTO_002;
import com.example.LightEpro.sch.dto.sch_003.SCH_RQ_DTO_003;
import com.example.LightEpro.sch.dto.sch_003.SCH_RS_DTO_003;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_002;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_003;
import com.example.LightEpro.sch.service.SCH_SERVICE_002;
import com.example.LightEpro.sch.service.SCH_SERVICE_003;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_003 implements SCH_SERVICE_003 {
    private final SCH_MAPPER_003 schMapper003;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SCH_RS_DTO_003 deleteSingleSch(SCH_RQ_DTO_003 schRqDto003) {
        int updateSchDelStCnt = schMapper003.updateSchDelStatus(schRqDto003);
        int updateSchUserDelStCnt = schMapper003.updateSchUsersDelStatus(schRqDto003);

        SCH_RS_DTO_003 schRsDto003 = SCH_RS_DTO_003.builder()
                .schmSeq(schRqDto003.getSch().getSchmSeq())
                .schSeq(schRqDto003.getSch().getSchmSeq())
                .updateSchDelStCnt(updateSchDelStCnt)
                .updateSchUserDelStCnt(updateSchUserDelStCnt)
                .build();

        return schRsDto003;
    }
}
