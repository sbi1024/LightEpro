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
    public static final String MASTER_AUTHORITY = "M";
    /**
     * 수정 권한 = W
     */
    public static final String WRITE_AUTHORITY = "W";
    /**
     * 조회 권한 = R
     */
    public static final String READ_AUTHORITY = "R";
    /**
     * 조회 권한 = R
     */
    public static final String UNKNOWN_AUTHORITY = "U";
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

    /**
     * 일정 모듈 API 타입 (캘린더에 관한 것인지,일정에 관한것인지)
     */
    public static final String SCHEDULE_TYPE = "SCHEDULE";

    /**
     * 일정 모듈 API 타입 (캘린더에 관한 것인지,일정에 관한것인지)
     */
    public static final String CALENDAR_TYPE = "CALENDAR";

    /**
     * API 등록 성격
     */
    public static final String CREATE_PERSONALITY = "CREATE";
    /**
     * API 조회 성격
     */
    public static final String FIND_PERSONALITY = "FIND";
    /**
     * API 수정 성격
     */
    public static final String MODIFY_PERSONALITY = "MODIFY";
    /**
     * API 삭제 성격
     */
    public static final String REMOVE_PERSONALITY = "REMOVE";
}
