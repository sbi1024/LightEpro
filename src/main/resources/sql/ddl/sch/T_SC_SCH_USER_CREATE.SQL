CREATE TABLE `T_SC_SCH_USER`
(
    `SCHM_SEQ`           BIGINT      NOT NULL COMMENT '일정 마스터 시퀀스',
    `SCH_SEQ`            BIGINT      NOT NULL COMMENT '일정 시퀀스',
    `CDE_SEQ`            BIGINT      NOT NULL COMMENT '회사/부서/개인 시퀀스',
    `CDE_TYPE`           VARCHAR(10) NOT NULL COMMENT '회사: C / 부서: D / 개인 타입: I',
    `SCH_PARTITION_TYPE` BIGINT      NOT NULL COMMENT '일정 구성원의 타입 , EX) 참여자 : 10 / 공개범위: 20',
    `SCH_AUTHORITY`      VARCHAR(10) NOT NULL COMMENT '일정 구성원의 수정 권한 , EX) M : 마스터 권한 , W : 수정 권한 , R : 조회 권한 ',
    `USE_YND`            VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME    NOT NULL DEFAULT NOW() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`SCHM_SEQ`, `SCH_SEQ`, `CDE_SEQ`, `CDE_TYPE`, `SCH_PARTITION_TYPE`)
) COMMENT '일정 구성원 테이블';