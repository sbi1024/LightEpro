package com.example.LightEpro.emp.dto.emp009;

import com.example.LightEpro.emp.dto.emp004.EmpRsDto004;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpRsDto009 {
    private List<CompInfo> compInfos;

    @Data
    public static class CompInfo {
        private String compSeq;
        private String parentCompSeq;
        private String compPath;
    }
}
