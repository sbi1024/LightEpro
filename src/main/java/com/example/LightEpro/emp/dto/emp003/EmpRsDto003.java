package com.example.LightEpro.emp.dto.emp003;

import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpRsDto003 {
    private DeptInfo deptInfo;

    @Data
    public static class DeptInfo {
        private String deptSeq;
        private String parentDeptSeq;
        private String deptName;
        private String useYnd;
        private String createDate;
        private String createSeq;
        private String modifyDate;
        private String modifySeq;
    }
}
