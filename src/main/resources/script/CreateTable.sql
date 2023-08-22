-- 1. 일정 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_SC_SCH;
CREATE TABLE IF NOT EXISTS `T_SC_SCH`
(
    `SCHM_SEQ`         BIGINT      NOT NULL COMMENT '일정 마스터 시퀀스',
    `SCH_SEQ`          BIGINT      NOT NULL COMMENT '일정 시퀀스',
    `START_DATE`       DATETIME    NOT NULL COMMENT '일정 시작 일자',
    `START_DATE_YEAR`  INT         NOT NULL COMMENT '일정 시작 연',
    `START_DATE_MONTH` INT         NOT NULL COMMENT '일정 시작 달',
    `END_DATE`         DATETIME    NOT NULL COMMENT '일정 종료 일자',
    `END_DATE_YEAR`    INT         NOT NULL COMMENT '일정 종료 연',
    `END_DATE_MONTH`   INT         NOT NULL COMMENT '일정 종료 달',
    `ALL_DAY_YN`       VARCHAR(3)  NOT NULL DEFAULT 'N' COMMENT '일정 종일 여부',
    `SCH_TITLE`        VARCHAR(50) NOT NULL COMMENT '일정 제목',
    `SCH_CONTENT`      TEXT        NULL     DEFAULT '' COMMENT '일정 내용',
    `USE_YND`          VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`      DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`       BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`      DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`       BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`      DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`       BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`SCHM_SEQ`, `SCH_SEQ`),
    INDEX IDX_CREATE_SEQ (`CREATE_SEQ`),
    INDEX IDX_START_DATE_SEQ (`START_DATE_YEAR`, `START_DATE_MONTH`),
    INDEX IDX_END_DATE_SEQ (`END_DATE_YEAR`, `END_DATE_MONTH`)
) COMMENT '일정 테이블';

-- 2. 일정 구성원 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_SC_SCH_USER;
CREATE TABLE IF NOT EXISTS `T_SC_SCH_USER`
(
    `SCHM_SEQ`           BIGINT      NOT NULL COMMENT '일정 마스터 시퀀스',
    `SCH_SEQ`            BIGINT      NOT NULL COMMENT '일정 시퀀스',
    `CDE_SEQ`            BIGINT      NOT NULL COMMENT '회사/부서/개인 시퀀스',
    `CDE_TYPE`           VARCHAR(10) NOT NULL COMMENT '회사: C / 부서: D / 개인 타입: E',
    `SCH_PARTITION_TYPE` BIGINT      NOT NULL COMMENT '일정 구성원의 타입 , EX) 참여자 : 10 / 공개범위: 20',
    `SCH_AUTORITY`       VARCHAR(10) NOT NULL COMMENT '일정 구성원의 수정 권한 , EX) M : 마스터 권한 , W : 수정 권한 , S : 조회 권한 ',
    `CAL_SEQ`            BIGINT      NOT NULL COMMENT '캘린더 시퀀스',
    `USE_YND`            VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`SCHM_SEQ`, `SCH_SEQ`, `CDE_SEQ`, `CDE_TYPE`),
    INDEX IDX_CAL_SEQ (`CAL_SEQ`)
) COMMENT '일정 구성원 테이블';

-- 3. 일정 캘린더 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_SC_CAL;
CREATE TABLE IF NOT EXISTS `T_SC_CAL`
(
    `CAL_SEQ`     BIGINT      NOT NULL COMMENT '캘린더 시퀀스',
    `CAL_TITLE`   VARCHAR(50) NOT NULL COMMENT '캘린더 제목',
    `CAL_CONTENT` TEXT        NULL     DEFAULT '' COMMENT '캘린더 내용',
    `CAL_COLOR`   VARCHAR(50) NULL     DEFAULT '#748FFC' COMMENT '캘린더 색상',
    `CAL_TYPE`    VARCHAR(10) NOT NULL COMMENT '캘린더 타입',
    `USE_YND`     VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE` DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`  BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE` DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`  BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE` DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`  BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`CAL_SEQ`)
) COMMENT '일정 캘린더 테이블'
;

-- 4. 일정 캘린더 구성원 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_SC_CAL_USER;
CREATE TABLE IF NOT EXISTS `T_SC_CAL_USER`
(
    `CAL_SEQ`            BIGINT      NOT NULL COMMENT '캘린더 시퀀스',
    `CDE_SEQ`            BIGINT      NOT NULL COMMENT '회사/부서/개인 시퀀스',
    `CDE_TYPE`           VARCHAR(10) NOT NULL COMMENT '회사: C / 부서: D / 개인 타입: E',
    `CAL_PARTITION_TYPE` BIGINT      NOT NULL COMMENT '일정 구성원의 타입 , EX) 소유자 : 100 / 관리자: 200',
    `CAL_AUTORITY`       VARCHAR(10) NOT NULL COMMENT '캘린더 구성원의 권한 , EX) M : 마스터 권한 , W : 수정 권한 , S : 조회 권한 ',
    `USE_YND`            VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`        DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`CAL_SEQ`, `CDE_SEQ`, `CDE_TYPE`, `CAL_PARTITION_TYPE`)
) COMMENT '일정 캘린더 구성원 테이블'
;

-- 5. 사원 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_EMP;
CREATE TABLE IF NOT EXISTS `T_EM_EMP`
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

-- 6. 회사 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_COMP;
CREATE TABLE IF NOT EXISTS `T_EM_COMP`
(
    `COMP_SEQ`        BIGINT      NOT NULL COMMENT '회사 시퀀스',
    `PARENT_COMP_SEQ` BIGINT      NULL COMMENT '상위 회사 시퀀스',
    `COMP_NAME`       VARCHAR(30) NOT NULL COMMENT '회사 이름(한국)',
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

-- 7. 부서 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_DEPT;
CREATE TABLE IF NOT EXISTS `T_EM_DEPT`
(
    `DEPT_SEQ`        BIGINT      NOT NULL COMMENT '부서 시퀀스',
    `PARENT_DEPT_SEQ` BIGINT      NULL COMMENT '상위 부서 시퀀스',
    `DEPT_NAME`       VARCHAR(30) NOT NULL COMMENT '부서 이름',
    `USE_YND`         VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE`     DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`      BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`     DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`      BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`     DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`      BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`DEPT_SEQ`)
) COMMENT '부서 테이블'
;

-- 8. 사용자 계정 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_EMP_ACCOUNT;
CREATE TABLE IF NOT EXISTS `T_EM_EMP_ACCOUNT`
(
    `EMP_ID`      VARCHAR(30) NOT NULL COMMENT '사용자 아이디',
    `EMP_PW`      VARCHAR(30) NOT NULL COMMENT '사용자 패스워드',
    `EMP_SEQ`     BIGINT      NOT NULL COMMENT '사원 시퀀스',
    `USE_YND`     VARCHAR(3)  NOT NULL DEFAULT 'Y' COMMENT '사용 여부',
    `CREATE_DATE` DATETIME    NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`  BIGINT      NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE` DATETIME    NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`  BIGINT      NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE` DATETIME    NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`  BIGINT      NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`EMP_ID`),
    INDEX `IDX_EMP_SEQ` (`EMP_SEQ`)
) COMMENT '사용자 계정 테이블'
;

-- 9. 사원 매핑 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_EMP_MAPPING;
CREATE TABLE IF NOT EXISTS `T_EM_EMP_MAPPING`
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
    `CREATE_DATE`        DATETIME   NOT NULL DEFAULT SYSDATE() COMMENT '최초 등록 일자',
    `CREATE_SEQ`         BIGINT     NOT NULL COMMENT '등록자 시퀀스',
    `MODIFY_DATE`        DATETIME   NULL COMMENT '최종 변경 일자',
    `MODIFY_SEQ`         BIGINT     NULL COMMENT '변경자 시퀀스',
    `DELETE_DATE`        DATETIME   NULL COMMENT '최종 삭제 일자',
    `DELETE_SEQ`         BIGINT     NULL COMMENT '삭제자 시퀀스',
    PRIMARY KEY (`EMP_SEQ`, `DEPT_SEQ`, `COMP_SEQ`)
) COMMENT '사원 매핑 테이블 (회사/부서)'
;

-- 10. 직책/직위/직급 테이블 삭제 및 생성
DROP TABLE IF EXISTS T_EM_POSITION;
CREATE TABLE IF NOT EXISTS `T_EM_POSITION`
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
    PRIMARY KEY (`COMP_SEQ`, `POSITION_CODE_SEQ`)
) COMMENT '직책/직위/직급 테이블'
;

