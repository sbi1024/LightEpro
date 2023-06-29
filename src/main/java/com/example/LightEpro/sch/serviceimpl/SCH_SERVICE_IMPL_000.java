package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.mapper.SCH_MAPPER_000;
import com.example.LightEpro.sch.mapper.SCH_MAPPER_001;
import com.example.LightEpro.sch.service.SCH_SERVICE_000;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_000 implements SCH_SERVICE_000 {

    private final SCH_MAPPER_000 schMapper;

    @Override
    public int findCurrentSchValue(Map<String,Object> request) throws Exception {
        int currentSchValue = schMapper.findCurrentSchValue(request);
        return currentSchValue;
    }

    @Override
    public Map<String, Object> insertSingleSch(Map<String, Object> request) throws Exception {
        // DB NAME 할당
        String dbName = String.valueOf(request.get("DB_NAME"));
        request.put("DB_NAME",dbName);
        // 시퀀스 값 할당
        int currentSchValue = findCurrentSchValue(request);
        // 요청자 empSeq 값 추출
        String empSeq = String.valueOf(request.get("empSeq"));

        // 일정 등록을 위한 요청 값 구성
        String schmSeq = String.valueOf(currentSchValue);
        String schSeq = String.valueOf(currentSchValue);
        String startDate = String.valueOf(request.get("startDate"));
        String endDate = String.valueOf(request.get("endDate"));
        String alldayYn = String.valueOf(request.get("alldayYn"));
        String schTitle = String.valueOf(request.get("schTitle"));
        String schContent = String.valueOf(request.get("schContent"));
        String useYnd = String.valueOf(request.get("useYnd"));
        String createSeq = empSeq;

        request.put("schmSeq",schmSeq);
        request.put("schSeq",schSeq);
        request.put("createSeq",createSeq);

        schMapper.insertSingleSch(request);


        Map<String,Object> resultData = new HashMap<>();
        resultData.put("schmSeq",schmSeq);
        resultData.put("schSeq",schSeq);
        return resultData;
    }
}
