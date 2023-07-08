package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_004.SCH_RQ_DTO_004;
import com.example.LightEpro.sch.dto.sch_004.SCH_RS_DTO_004;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_004;
import com.example.LightEpro.sch.service.SCH_SERVICE_004;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_004 implements SCH_SERVICE_004 {
    private final SCH_MAPPER_004 schMapper004;
    @Override
    public SCH_RS_DTO_004 selectSchList(SCH_RQ_DTO_004 schRqDto004) {
        List<SCH_RS_DTO_004.SchInfo> schInfos = schMapper004.selectSchList(schRqDto004);

        SCH_RS_DTO_004 schRsDto004 = SCH_RS_DTO_004.builder()
                .schInfos(schInfos)
                .build();


        return schRsDto004;
    }
}
