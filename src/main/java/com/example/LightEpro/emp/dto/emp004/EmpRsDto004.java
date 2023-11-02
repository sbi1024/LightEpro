package com.example.LightEpro.emp.dto.emp004;

import com.example.LightEpro.emp.dto.emp003.EmpRsDto003;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmpRsDto004 {
    private List<Department> departments;
    private int departmentsCnt;

    @Data
    public static class Department {
        private String deptSeq;
        private String parentDeptSeq;
        private String deptPath;
    }
}
