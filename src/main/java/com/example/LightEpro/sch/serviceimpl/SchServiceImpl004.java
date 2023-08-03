package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch004.SchRqDto004;
import com.example.LightEpro.sch.dto.sch004.SchRsDto004;
import com.example.LightEpro.sch.mapper.SchMapper004;
import com.example.LightEpro.sch.service.SchService004;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchServiceImpl004 implements SchService004 {
    private final SchMapper004 schMapper004;

    @Override
    public SchRsDto004 selectSchList(SchRqDto004 schRqDto004) throws Exception {
        List<SchRsDto004.SchInfo> schInfos = schMapper004.selectSchList(schRqDto004);

        SchRsDto004 schRsDto004 = SchRsDto004.builder()
                .schInfos(schInfos)
                .build();

        return schRsDto004;
    }
}
