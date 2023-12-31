package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import com.example.LightEpro.sch.exception.ExceptionCustom;
import com.example.LightEpro.sch.mapper.SchMapper001;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl001 implements SchService001 {
    private final SchMapper001 schMapper001;
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto001 findSingleSch(SchRqDto001 schRqDto001) throws Exception{
        log.info("findSingleSch Method Start !!!");
        log.info("findSingleSch Method Request Data : " + schRqDto001);

        SchRsDto001.Sch sch = schMapper001.findSchBySchmSeqAndSchSeq(schRqDto001);
        List<SchRsDto001.Participant> participants = schMapper001.findParticipantsBySchmSeqAndSchSeq(schRqDto001);
        List<SchRsDto001.DisclosureScope> disclosureScopes = schMapper001.findDisclosureScopesBySchmSeqAndSchSeq(schRqDto001);

        SchRsDto001 schRsDto001 = SchRsDto001.builder()
                .sch(sch)
                .participants(participants)
                .disclosureScopes(disclosureScopes)
                .build();

        log.info("createSingleSch Method Return Data : " + schRsDto001);
        log.info("createSingleSch Method End !!!");
        // return
        return schRsDto001;
    }
}
