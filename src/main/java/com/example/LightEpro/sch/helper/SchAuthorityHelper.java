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
        // 해당 모듈의 API 성격 확인 (등록/조회/수정/삭제)
        String moduleApiPersonality = schRqDto999.getModuleApiPersonality();
        // 타입에 맞는 권한 분기 처리 메소드 호출
        if (moduleApiType.equalsIgnoreCase(SchConstValue.CALENDAR_TYPE)) {
            return calendarBranchProcessing(schRqDto999, moduleApiPersonality);
        } else if (moduleApiType.equalsIgnoreCase(SchConstValue.SCHEDULE_TYPE)) {
            return scheduleBranchProcessing(schRqDto999, moduleApiPersonality);
        } else {
            return false;
        }
    }

    // 캘린더 권한 분기 처리 메소드
    public boolean calendarBranchProcessing(SchRqDto999 schRqDto999, String moduleApiPersonality) throws Exception {

        // 캘린더 권한 확인 메소드 호출
        SchRsDto999 calendarAuthority = confirmCalendarAuthorityInfo(schRqDto999);
        // calendarAuthority 로 부터 authority 값을 추출한다.
        String getCalAuthority = calendarAuthority == null ? null : calendarAuthority.getAuthority();

        /** 등록인 경우 (최초 등록에 해당하기 때문에 true 반환) */
        if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.CREATE_PERSONALITY)) {
            return true;
        }

        /** 조회인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.FIND_PERSONALITY)) {
            // null 값인 경우 false 를 반환한다.
            if (getCalAuthority == null) {
                return false;
            }
            // 캘린더 권한이 마스터 이거나, 쓰기 권한인 경우
            else if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY)
                    || getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }

        /** 수정인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.MODIFY_PERSONALITY)) {
            // null 값인 경우 false 를 반환한다.
            if (getCalAuthority == null) {
                return false;
            }
            // 캘린더 권한이 마스터 이거나, 쓰기 권한인 경우 true 를 반환한다.
            else if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY)
                    || getCalAuthority.equalsIgnoreCase(SchConstValue.WRITE_AUTHORITY)) {
                return true;
            }
            // 이외의 경우는 false 를 반환한다.
            else {
                return false;
            }
        }

        /** 삭제인 경우 */
        else if (moduleApiPersonality.equalsIgnoreCase(SchConstValue.REMOVE_PERSONALITY)) {
            // null 값인 경우 false 를 반환한다.
            if (getCalAuthority == null) {
                return false;
            }
            // 캘린더 권한이 마스터 권한인 경우 true 를 반환한다
            else if (getCalAuthority.equalsIgnoreCase(SchConstValue.MASTER_AUTHORITY)) {
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
    public boolean scheduleBranchProcessing(SchRqDto999 schRqDto999, String moduleApiPersonality) throws Exception {

        return false;
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

    // 일정 권한 확인 메소드
    public SchRsDto999 confirmScheduleAuthorityInfo(SchRqDto999 schRqDto999) throws Exception {
        // method start log
        log.info("confirmCalendarAuthorityInfo Method Start !!!");
        log.info("confirmCalendarAuthorityInfo Method Request Data : " + schRqDto999);

        // 일정 권한 확인 Mapper 호출
        SchRsDto999 schRsDto999 = schMapper999.confirmScheduleAuthority(schRqDto999);

        // method end log
        log.info("confirmCalendarAuthorityInfo Method Return Data : " + schRsDto999);
        log.info("confirmCalendarAuthorityInfo Method End !!!");

        // return
        return schRsDto999;
    }
}
