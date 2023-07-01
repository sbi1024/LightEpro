CREATE TABLE `T_SC_CAL_USER`
(
    `CAL_SEQ`            BIGINT      NOT NULL COMMENT '캘린더 시퀀스',
    `CDE_SEQ`            BIGINT      NOT NULL COMMENT '회사/부서/개인 시퀀스',
    `CDE_TYPE`           VARCHAR(10) NOT NULL COMMENT '회사: C / 부서: D / 개인 타입: E',
    `CAL_PARTITION_TYPE` VARCHAR(10) NOT NULL COMMENT '일정 구성원의 타입 , EX) 소유자 : 100 / 관리자: 200',
    `CAL_AUTORITY`       VARCHAR(10) NOT NULL COMMENT '캘린더 구성원의 권한 , EX) M : 마스터 권한 , W : 수정 권한 , S : 조회 권한 ',
    `USE_YND`            VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`CAL_SEQ`, `CDE_SEQ`, `CDE_TYPE`, `CAL_PARTITION_TYPE`)
) COMMENT '일정 캘린더 구성원 테이블'
;
