package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper001;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchServiceImpl001 implements SchService001 {
    private final SchMapper001 schMapper001;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto001 findSingleSch(SchRqDto001 schRqDto001) throws Exception{
        SchRsDto001.Sch sch = schMapper001.findSchBySchmSeqAndSchSeq(schRqDto001);
        // 일정조회시 , 존재하지 않는 일정인 경우 Exception 처리 진행
        if(sch == null){
            throw new ExceptionCustom.NotFountSchException();
        }

        List<SchRsDto001.Participant> participants = schMapper001.findParticipantsBySchmSeqAndSchSeq(schRqDto001);
        List<SchRsDto001.DisclosureScope> disclosureScopes = schMapper001.findDisclosureScopesBySchmSeqAndSchSeq(schRqDto001);

        SchRsDto001 schRsDto001 = SchRsDto001.builder()
                .sch(sch)
                .participants(participants)
                .disclosureScopes(disclosureScopes)
                .build();

        return schRsDto001;
    }
}
