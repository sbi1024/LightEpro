package com.example.LightEpro.sch.service;

import java.util.List;
import java.util.Map;

public interface SCH_SERVICE_001 {
    List<Map<String, Object>> findSchTitleBySchmSeq(Map<String, Object> request) throws Exception;
}
