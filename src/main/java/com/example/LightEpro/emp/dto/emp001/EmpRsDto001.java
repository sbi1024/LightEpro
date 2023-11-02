package com.example.LightEpro.emp.dto.emp001;

import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmpRsDto001 {
    private Department department;

    @Data
    public static class Department {
        private String deptSeq;
        private String parentDeptSeq;
        private String deptName;
        private String compSeq;
        private String useYnd;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
    }
}
