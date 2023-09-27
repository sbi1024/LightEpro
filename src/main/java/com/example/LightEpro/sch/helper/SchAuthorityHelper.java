package com.example.LightEpro.sch.helper;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch999.SchRqDto999;
import com.example.LightEpro.sch.dto.sch999.SchRsDto999;
import com.example.LightEpro.sch.mapper.SchMapper999;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchAuthorityHelper {
    // schMapper999 선언
    private final SchMapper999 schMapper999;

    // 권한 확인 메소드
    public boolean confirmAuthorityInfo(SchRqDto999 schRqDto999) throws Exception {
        // 캘린더/일정 모듈 타입 확인
        String moduleApiType = schRqDto999.getModuleApiType();
        // 타입에 맞는 권한 분기 처리 메소드 호출
        if (moduleApiType.equalsIgnoreCase(SchConstValue.CALENDAR_TYPE)) {
            // moduleApiType = 캘린더인 경우, 캘린더 권한 분기 처리 메소드 호출
            return calendarBranchProcessing(schRqDto999);
        } else if (moduleApiType.equalsIgnoreCase(SchConstValue.SCHEDULE_TYPE)) {
            // moduleApiType = 일정인 경우, 일정 권한 분기 처리 메소드 호출
            return scheduleBranchProcessing(schRqDto999);
        } else {
            // 이외의 경우는 false 를 반환한다.
            return false;
        }
    }

    // 캘린더 권한 분기 처리 메소드
    public boolean calendarBranchProcessing(SchRqDto999 schRqDto999) throws Exception {
        // 캘린더 권한 확인 메소드 호출
        SchRsDto999 calendarAuthority = confirmCalendarAuthorityInfo(schRqDto999);
        // calendarAuthority 로 부터 authority 값을 추출한다.
        String getCalAuthority = calendarAuthority == null ? SchConstValue.UNKNOWN_AUTHORITY : calendarAuthority.getAuthority();
        // 캘린더를 통한 일정 권한 확인 메소드 호출
        SchRsDto999 scheduleAuthorityByCalendar = confirmScheduleAuthorityByCalendarInfo(schRqDto999);
        // scheduleAuthorityByCalendar 로 부터 authority 값 추출
        String getSchAuthorityByCal = scheduleAuthorityByCalendar == null ? SchConstValue.UNKNOWN_AUTHORITY : scheduleAuthorityByCalendar.getAuthority();
        // 해당 모듈의 API 성격 확인 (등록/조회/수정/삭제)
        String moduleApiPersonality = schRqDto999.getModuleApiPersonality();

        /** 등록인 경우 (최초 등록에 해당하기 때문에 true 반환) */
        if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.CREATE_PERSONALITY)) {
            return true;
        }
        /** 조회인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.FIND_PERSONALITY)) {
            // 캘린더 권한이 마스터 이거나, 쓰기 권한이거나, 현재 조회하고자 하는 캘린더에 포함된 일정 구성원 중, 마스터/쓰기/읽기 권한을 가진 구성원인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getSchAuthorityByCal.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getSchAuthorityByCal.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getSchAuthorityByCal.equalsIgnoreCase(SchConstValue.READ_AUTHORITY)) {
                return true;
            } else {
                // 이외의 경우는 false 를 반환한다.
                return false;
            }
        }
        /** 수정인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.MODIFY_PERSONALITY)) {
            // 캘린더 권한이 마스터 이거나, 쓰기 권한인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 삭제인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.REMOVE_PERSONALITY)) {
            // 캘린더 권한이 마스터 권한인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 이외인 경우 */
        else {
            return false;
        }
    }

    // 일정 권한 분기 처리 메소드
    public boolean scheduleBranchProcessing(SchRqDto999 schRqDto999) throws Exception {
        // 캘린더 권한 확인 메소드 호출
        SchRsDto999 calendarAuthority = confirmCalendarAuthorityInfo(schRqDto999);
        // calendarAuthority 로 부터 authority 값을 추출한다.
        String getCalAuthority = calendarAuthority == null ? SchConstValue.UNKNOWN_AUTHORITY : calendarAuthority.getAuthority();
        // 일정 권한 확인 메소드 호출
        SchRsDto999 scheduleAuthority = confirmScheduleAuthorityInfo(schRqDto999);
        // scheduleAuthority 로 부터 authority 값을 추출한다.
        String getSchAuthority = scheduleAuthority == null ? SchConstValue.UNKNOWN_AUTHORITY : scheduleAuthority.getAuthority();
        // 일정을 통한 캘린더 권한 확인 메소드 호출
        SchRsDto999 calendarAuthorityBySchedule = confirmCalendarAuthorityByScheduleInfo(schRqDto999);
        // calendarAuthorityBySchedule 로 부터 authority 값을 추출한다.
        String getCalAuthorityBySch = calendarAuthorityBySchedule == null ? SchConstValue.UNKNOWN_AUTHORITY : calendarAuthorityBySchedule.getAuthority();
        // 해당 모듈의 API 성격 확인 (등록/조회/수정/삭제)
        String moduleApiPersonality = schRqDto999.getModuleApiPersonality();

        /** 등록인 경우 */
        if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.CREATE_PERSONALITY)) {
            // 캘린더 권한이 마스터 이거나, 쓰기 권한인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 조회인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.FIND_PERSONALITY)) {
            // 캘린더의 권한이 마스터 이거나, 쓰기권한 이거나, 일정의 권한이 마스터 이거나, 쓰기권한이거나, 읽기권한인 경우
            if (getCalAuthorityBySch.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthorityBySch.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getCalAuthorityBySch.equalsIgnoreCase(SchConstValue.READ_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.READ_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 수정인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.MODIFY_PERSONALITY)) {
            // 캘린더의 권한이 마스터이거나, 쓰기권한이거나, 일정의 마스터권한이거나, 쓰기권한인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 삭제인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.REMOVE_PERSONALITY)) {
            // 캘린더의 권한이 마스터 이거나, 쓰기권한이거나, 일정의 권한이 마스터 권한인 경우
            if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY) ||
                    getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY) ||
                    getSchAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }
        /** 이외인 경우 */
        else {
            return false;
        }
    }


    // 캘린더 권한 확인 메소드
    public SchRsDto999 confirmCalendarAuthorityInfo(SchRqDto999 schRqDto999) throws Exception {
        // method start log
        log.info("confirmCalendarAuthorityInfo Method Start !!!");
        log.info("confirmCalendarAuthorityInfo Method Request Data : " + schRqDto999);

        // 캘린더 권한 확인 Mapper 호출
        SchRsDto999 schRsDto999 = schMapper999.confirmCalendarAuthority(schRqDto999);

        // method end log
        log.info("confirmCalendarAuthorityInfo Method Return Data : " + schRsDto999);
        log.info("confirmCalendarAuthorityInfo Method End !!!");

        // return
        return schRsDto999;
    }

    // 캘린더를 통한 일정 권한 확인 메소드 
    public SchRsDto999 confirmScheduleAuthorityByCalendarInfo(SchRqDto999 schRqDto999) throws Exception {
        // method start log
        log.info("confirmScheduleAuthorityByCalendarInfo Method Start !!!");
        log.info("confirmScheduleAuthorityByCalendarInfo Method Request Data : " + schRqDto999);

        // 캘린더를 통한 일정 권한 확인 Mapper 호출
        SchRsDto999 schRsDto999 = schMapper999.confirmScheduleAuthorityByCalendar(schRqDto999);

        // method end log
        log.info("confirmScheduleAuthorityByCalendarInfo Method Return Data : " + schRsDto999);
        log.info("confirmScheduleAuthorityByCalendarInfo Method End !!!");

        // return
        return schRsDto999;
    }

    // 일정 권한 확인 메소드
    public SchRsDto999 confirmScheduleAuthorityInfo(SchRqDto999 schRqDto999) throws Exception {
        // method start log
        log.info("confirmScheduleAuthorityInfo Method Start !!!");
        log.info("confirmScheduleAuthorityInfo Method Request Data : " + schRqDto999);

        // 일정 권한 확인 Mapper 호출
        SchRsDto999 schRsDto999 = schMapper999.confirmScheduleAuthority(schRqDto999);

        // method end log
        log.info("confirmScheduleAuthorityInfo Method Return Data : " + schRsDto999);
        log.info("confirmScheduleAuthorityInfo Method End !!!");

        // return
        return schRsDto999;
    }

    // 일정을 통한 캘린더 권한 확인 메소드
    public SchRsDto999 confirmCalendarAuthorityByScheduleInfo(SchRqDto999 schRqDto999) throws Exception {
        // method start log
        log.info("confirmScheduleAuthorityInfo Method Start !!!");
        log.info("confirmScheduleAuthorityInfo Method Request Data : " + schRqDto999);

        // 일정을 통한 캘린더 권한 확인 Mapper 호출
        SchRsDto999 schRsDto999 = schMapper999.confirmCalendarAuthorityBySchedule(schRqDto999);

        // method end log
        log.info("confirmScheduleAuthorityInfo Method Return Data : " + schRsDto999);
        log.info("confirmScheduleAuthorityInfo Method End !!!");

        // return
        return schRsDto999;
    }
}
