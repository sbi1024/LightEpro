package com.example.LightEpro.emp.dto.emp006;

import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpRsDto006 {
    private CompInfo compInfo;

    @Data
    public static class CompInfo {
        private String compSeq;
        private String parentCompSeq;
        private String compName;
        private String useYnd;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
    }
}
