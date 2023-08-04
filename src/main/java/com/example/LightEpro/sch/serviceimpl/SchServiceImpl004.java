package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl004 implements SchService004 {
    private final SchMapper004 schMapper004;

    @Override
    public SchRsDto004 selectSchList(SchRqDto004 schRqDto004) throws Exception {
        log.info("selectSchList Method Start !!!");
        log.info("selectSchList Method Request Data : " + schRqDto004);

        List<SchRsDto004.SchInfo> schInfos = schMapper004.selectSchList(schRqDto004);

        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .schInfos(schInfos)
                .build();

        log.info("selectSchList Method Return Data : " + schRqDto004);
        log.info("selectSchList Method End !!!");
        // return
        return schRsDto004;
    }
}
