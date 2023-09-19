package com.example.LightEpro.sch.constant;

/**
 * 일정 모듈 상수 모음
 */
public class SchConstValue {
    /**
     * 빈 문자열 변수 = ""
     */
    public static final String EMPTY_VALUE = "";
    /**
     * 0 문자열 변수 = ""
     */
    public static final String ZERO_VALUE = "0";
    /**
     * 캘린더 타입 개인 캘린더 = I
     */
    public static final String CAL_I_TYPE = "I";
    /**
     * 캘린더 타입 공유 캘린더 = S
     */
    public static final String CAL_S_TYPE = "S";

    /**
     * 일정 구성원 타입  (개인) = I
     */
    public static final String CDE_I_TYPE = "I";
    /**
     * 일정 구성원 타입  (부서) = D
     */
    public static final String CDE_D_TYPE = "D";
    /**
     * 일정 구성원 타입  (회사) = C
     */
    public static final String CDE_C_TYPE = "C";

    /**
     * 일정 구성원 타입  (참여자) = 10
     */
    public static final int PARTICIPANT_TYPE = 10;

    /**
     * 일정 구성원 타입  (공개범위) = 20
     */
    public static final int DISCLOSURESCOPE_TYPE = 20;

    /**
     * 캘린더 구성원 타입  (소유자) = 100
     */
    public static final int OWNER_TYPE = 100;

    /**
     * 캘린더 구성원 타입  (관리자) = 200
     */
    public static final int MANAGER_TYPE = 200;
    /**
     * 마스터 권한 = M
     */
    public static final String MASTER_AUTORITY = "M";
    /**
     * 수정 권한 = W
     */
    public static final String WRITE_AUTORITY = "W";
    /**
     * 조회 권한 = R
     */
    public static final String READ_AUTORITY = "R";
    /**
     * 데이터 상태값 = Y (사용)
     */
    public static final String STATUS_Y = "Y";
    /**
     * 데이터 상태값 = N (미사용)
     */
    public static final String STATUS_N = "N";
    /**
     * 데이터 상태값 = D (영구 삭제 대기)
     */
    public static final String STATUS_D = "D";
}
