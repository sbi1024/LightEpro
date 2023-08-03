package com.example.LightEpro.sch.dto.sch005;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchRsDto005 {
    private int calSeq;
    private int insertCalRow;
    private int insertCalOwnerRow;
    private int insertCalManagerRow;
}
