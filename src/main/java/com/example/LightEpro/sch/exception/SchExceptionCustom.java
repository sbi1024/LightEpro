package com.example.LightEpro.sch.exception;

public class SchExceptionCustom {
    /**
     * 디비상에 존재하지 않는 일정을 찾는 경우 발생 Exception
     */
    public static class NotFoundSchException extends Exception {
    }

    /**
     * 일정의 시작일자 값이 종료일자보다 큰 경우 발생 Exception
     */
    public static class NotValidSchStartEndDateException extends Exception {
    }

    /**
     * 개인 캘린더 등록시에 , 참여자 혹은 공개범위 값이 요청값에 포함되는 경우 발생 Exception
     */
    public static class IncorrectIncludException extends Exception {
    }

    /**
     * 요청 값 중 , 캘린더 시퀀스 리스트 값에 대한 유효성 검증이 실패한 경우 발생 Exception
     */
    public static class NotValidCalSeqsException extends Exception {
    }
    /**
     * 디비상에 존재하지 않는 캘린더를 찾는 경우 발생 Exception
     */
    public static class NotFoundCalException extends Exception {
    }
}
