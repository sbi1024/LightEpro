package com.example.LightEpro.emp.dto.emp009;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpRsDto009 {
    private List<Company> companies;
    private int companiesCnt;

    @Data
    public static class Company {
        private String compSeq;
        private String parentCompSeq;
        private String compPath;
    }
}
