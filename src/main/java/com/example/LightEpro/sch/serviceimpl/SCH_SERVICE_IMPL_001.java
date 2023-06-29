package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.mapper.SCH_MAPPER_001;
import com.example.LightEpro.sch.service.SCH_SERVICE_001;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SCH_SERVICE_IMPL_001 implements SCH_SERVICE_001 {

    private final SCH_MAPPER_001 schMapper;


    @Override
    public List<Map<String, Object>> findSchTitleBySchmSeq(Map<String, Object> request) throws Exception {
        List<Map<String, Object>> returnData = null;
        try {
            returnData = schMapper.findSchTitleBySchmSeq();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return returnData;
    }
}
