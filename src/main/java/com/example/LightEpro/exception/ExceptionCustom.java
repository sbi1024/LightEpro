package com.example.LightEpro.exception;

public class ExceptionCustom {
    /**
     * 존재하지 않는 일정을 찾는 경우 발생 Exception
     */
    public static class NotFoundSchException extends Exception {
    }

    /**
     * 일정의 시작일자 값이 종료일자보다 큰 경우 발생 Exception
     */
    public static class NotBeGreaterThanEndDateException extends Exception {
    }

    /**
     * 개인 캘린더에서의 일정 등록/수정시에 공개범위 값이 요청값에 포함되는 경우 발생 Exception
     */
    public static class NotBeIncludedDisclosureException extends Exception {
    }

    /**
     * 요청 값 중 , 캘린더 시퀀스 리스트 값에 대한 유효성 검증이 실패한 경우 발생 Exception
     */
    public static class NotIncludedCalendarSequencesException extends Exception {
    }

    /**
     * 존재하지 않는 캘린더인 경우 발생 Exception
     */
    public static class NotFoundCalException extends Exception {
    }

    /**
     * 참여자 요청값 중 등록자 값이 존재하지 않는 경우 경우 발생 Exception
     */
    public static class NotFoundRegistrantException extends Exception {

    }

    /**
     * 캘린더 구성원 요청값 중 소유자 값이 존재하지 않는 경우 경우 발생 Exception
     */
    public static class NotFoundOwnerException extends Exception {

    }

    /**
     * 개인 캘린더에서의 캘린더 등록/수정시에 관리자 값이 요청값에 포함되는 경우 발생 Exception
     */
    public static class NotBeIncludedManagerException extends Exception {

    }


    /**
     * 조직도 시스템에서 존재하지 않는 사용자인 경우 경우 발생 Exception
     */
    public static class NotFoundUserException extends Exception {

    }

    /**
     * 잘못된 권한 있는 캘린더 시퀀스 값이 요청값에 포함되는 경우 발생 Exception
     */
    public static class NotMatchAuthorizedCalendarSequencesException extends Exception {

    }

    /**
     * 잘못된 권한 없는 캘린더 시퀀스 값이 요청값에 포함되는 경우 발생 Exception
     */
    public static class NotMatchUnAuthorizedCalendarSequencesException extends Exception {

    }

    /**
     * 일정 등록 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForSchCreateException extends Exception {

    }

    /**
     * 일정 조회 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForSchFindException extends Exception {

    }

    /**
     * 일정 수정 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForSchModifyException extends Exception {

    }

    /**
     * 일정 삭제 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForSchRemoveException extends Exception {

    }
    
    /**
     * 캘린더 등록 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForCalCreateException extends Exception {

    }

    /**
     * 캘린더 조회 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForCalFindException extends Exception {

    }

    /**
     * 캘린더 수정 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForCalModifyException extends Exception {

    }

    /**
     * 캘린더 삭제 권한이 없는 경우 발생 Exception
     */
    public static class NotAuthorizedForCalRemoveException extends Exception {

    }
}
