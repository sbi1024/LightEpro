package com.example.LightEpro.sch.service;

import java.util.List;
import java.util.Map;

public interface SCH_SERVICE_000 {
    int findCurrentSchValue(Map<String,Object> request) throws Exception;
    Map<String, Object> insertSingleSch(Map<String, Object> request) throws Exception;
}
