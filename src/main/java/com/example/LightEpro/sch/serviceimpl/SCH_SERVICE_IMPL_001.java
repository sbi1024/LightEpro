package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch_001.SCH_RQ_DTO_001;
import com.example.LightEpro.sch.dto.sch_001.SCH_RS_DTO_001;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_001;
import com.example.LightEpro.sch.service.SCH_SERVICE_001;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_001 implements SCH_SERVICE_001 {
    private final SCH_MAPPER_001 schMapper001;

    @Override
    public SCH_RS_DTO_001 findSingleSch(SCH_RQ_DTO_001 schRqDto001) {
        SCH_RS_DTO_001.Sch sch = schMapper001.findSchBySchmSeqAndSchSeq(schRqDto001);
        List<SCH_RS_DTO_001.Participant> participants = schMapper001.findParticipantsBySchmSeqAndSchSeq(schRqDto001);
        List<SCH_RS_DTO_001.DisclosureScope> disclosureScopes = schMapper001.findDisclosureScopesBySchmSeqAndSchSeq(schRqDto001);

        SCH_RS_DTO_001 schRsDto001 = SCH_RS_DTO_001.builder()
                .sch(sch)
                .participants(participants)
                .disclosureScopes(disclosureScopes)
                .build();

        return schRsDto001;
    }
}
