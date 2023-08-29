CREATE TABLE `T_EM_EMP_MAPPING`
(
    `EMP_SEQ`            BIGINT     NOT NULL COMMENT '사원 시퀀스',
    `DEPT_SEQ`           BIGINT     NOT NULL COMMENT '부서 시퀀스',
    `COMP_SEQ`           BIGINT     NOT NULL COMMENT '회사 시퀀스',
    `MAIN_DEPT_YN`       VARCHAR(3) NOT NULL COMMENT '주 부서 여부',
    `MAIN_COMP_YN`       VARCHAR(3) NOT NULL COMMENT '주 회사 여부',
    `POSITION_SEQ`       BIGINT     NOT NULL COMMENT '직책 시퀀스',
    `POSITION_SPOT_SEQ`  BIGINT     NOT NULL COMMENT '직위 시퀀스',
    `POSITION_GRADE_SEQ` BIGINT     NOT NULL COMMENT '직급 시퀀스',
    `USE_YND`            VARCHAR(3) NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME   NOT NULL DEFAULT NOW() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT     NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME   NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT     NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME   NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT     NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`EMP_SEQ`, `DEPT_SEQ`, `COMP_SEQ`),
    INDEX IDX_DEPT_SEQ (`DEPT_SEQ`),
    INDEX IDX_COMP_SEQ (`COMP_SEQ`)
) COMMENT '사원 매핑 테이블 (회사/부서)'
;