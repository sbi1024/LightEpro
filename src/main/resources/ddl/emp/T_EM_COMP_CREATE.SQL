CREATE TABLE `T_EM_COMP`
(
    `COMP_SEQ`        BIGINT      NOT NULL COMMENT '회사 시퀀스',
    `PARENT_COMP_SEQ` BIGINT      NULL COMMENT '상위 회사 시퀀스',
    `COMP_PATH`       VARCHAR(30) NOT NULL COMMENT '회사 경로',
    `COMP_NAME_KR`    VARCHAR(30) NOT NULL COMMENT '회사 이름(한국)',
    `COMP_NAME_EN`    VARCHAR(30) NULL COMMENT '회사 이름(영어)',
    `COMP_NAME_CN`    VARCHAR(30) NULL COMMENT '회사 이름(중국)',
    `COMP_NAME_JP`    VARCHAR(30) NULL COMMENT '회사 이름(일본)',
    `USE_YND`         VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`     DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`      BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`     DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`      BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`     DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`      BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`COMP_SEQ`)
) COMMENT '회사 테이블'
;