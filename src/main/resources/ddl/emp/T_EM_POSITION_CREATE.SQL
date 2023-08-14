CREATE TABLE `T_EM_POSITION`
(
    `COMP_SEQ`           BIGINT      NOT NULL COMMENT '회사 시퀀스',
    `POSITION_CODE_SEQ`  BIGINT      NOT NULL COMMENT '직책/직위/직급 시퀀스',
    `POSITION_CODE_INFO` VARCHAR(30) NOT NULL COMMENT '직책/직위/직급 정보',
    `USE_YND`            VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`COMP_SEQ`,`POSITION_CODE_SEQ`)
) COMMENT '직책/직위/직급 테이블'
;