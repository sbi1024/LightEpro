CREATE TABLE `T_EM_EMP`
(
    `EMP_SEQ`          BIGINT      NOT NULL COMMENT '사원 시퀀스',
    `EMP_NAME`         VARCHAR(30) NOT NULL COMMENT '사원 이름',
    `EMP_BIRTH_DATE`   DATE        NOT NULL COMMENT '사원 태어난 일자',
    `EMP_HIRE_DATE`    DATE        NOT NULL COMMENT '사원 입사 일자',
    `EMP_FIRE_DATE`    DATE        NULL COMMENT '사원 퇴사 일자',
    `EMP_PHONE_NUMBER` VARCHAR(30) NULL COMMENT '사원 휴대폰 번호',
    `EMP_SEX`          VARCHAR(3)  NULL COMMENT '성별',
    `USE_YND`          VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`      DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`       BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`      DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`       BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`      DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`       BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`EMP_SEQ`)
) COMMENT '사원 테이블'
;