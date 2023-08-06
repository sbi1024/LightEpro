package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.service.SchServiceTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImplTest implements SchServiceTest {

    private final SchServiceImpl000 schServiceImpl000;

    @Override
    public Object createTestSch(SchRqDto000 schRqDto000) throws Exception{
        for(int i = 0 ; i < 20000; i++){
            schServiceImpl000.createSingleSch(schRqDto000);
        }
        return null;
    }
}
