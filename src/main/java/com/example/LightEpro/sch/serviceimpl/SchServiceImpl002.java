package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.service.SchService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl002 implements SchService002 {
    private final SchMapper002 schMapper002;
    
    // TODO insertParticipants 쿼리 재확인 필요

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto002 modifyScheduleInfo(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("modifyScheduleInfo Method Start !!!");
        log.info("modifyScheduleInfo Method Request Data : " + schRqDto002);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRqDto002);
        // 단일 일정 수정 메소드 호출
        modifySchedule(schRqDto002);
        // 일정 참여자 등록 메소드 호출
        createParticipants(schRqDto002);
        // 일정 공개범위 등록 메소드 호출
        createDisclosureScopes(schRqDto002);

        // schRsDto002 객체 builder 패턴을 통해 객체 생성
        SchRsDto002 schRsDto002 = SchRsDto002.builder()
                .schmSeq(schRqDto002.getSchedule().getSchmSeq())
                .schSeq(schRqDto002.getSchedule().getSchSeq())
                .build();

        // method end log
        log.info("modifyScheduleInfo Method Return Data : " + schRsDto002);
        log.info("modifyScheduleInfo Method End !!!");

        // return
        return schRsDto002;
    }


    // 일정 시퀀스 번호 주입 메소드
    @Override
    public void assignObject(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto002);

        // schedule 객체 생성
        SchRqDto002.Schedule schedule = schRqDto002.getSchedule();

        // startDate , endDate 값 추출
        LocalDateTime startDate = schedule.getStartDate();
        LocalDateTime endDate = schedule.getEndDate();

        // year month 값 재 할당 (startDate , endDate)
        schedule.setStartDateYear(startDate.getYear());
        schedule.setStartDateMonth(startDate.getMonthValue());
        schedule.setEndDateYear(endDate.getYear());
        schedule.setEndDateMonth(endDate.getMonthValue());

        // 일정 참여자 및 공개범위 일치 비일치 추출
        confirmCalendarUsers(schRqDto002);

        // method end log
        log.info("assignObject Method Result Data : " + schRqDto002);
        log.info("assignObject Method End !!!");
    }

    @Override
    public void confirmCalendarUsers(SchRqDto002 schRqDto002) throws Exception {

    }

    // 단일 일정 수정 메소드
    @Override
    public int modifySchedule(SchRqDto002 schRqDto002) throws Exception {
        log.info("updateSingleSch Method Start !!!");
        log.info("updateSingleSch Method Request Data : " + schRqDto002);

        int updateSchCnt = schMapper002.updateSchedule(schRqDto002);

        log.info("updateSingleSch Method Return Data : " + updateSchCnt);
        log.info("updateSingleSch Method End !!!");
        return updateSchCnt;
    }

    // 일정 참여자 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 10 (참여자))
    @Override
    public int createParticipants(SchRqDto002 schRqDto002) throws Exception {
        log.info("insertSchParticipants Method Start !!!");
        log.info("insertSchParticipants Method Request Data : " + schRqDto002);

        // 참여자 변수 선언
        List<SchRqDto002.Participant> participants = schRqDto002.getParticipants();
        if (participants == null || participants.size() == 0) {
            log.info("insertSchParticipants Method participants == null or participants.size() == 0");
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertRow = schMapper002.insertParticipants(schRqDto002);

        log.info("insertSchParticipants Method Return Data : " + insertRow);
        log.info("insertSchParticipants Method End !!!");
        // return
        return insertRow;
    }

    @Override
    public int modifyParticipants(SchRqDto002 schRqDto002) throws Exception {
        return 0;
    }

    @Override
    public int removeParticipants(SchRqDto002 schRqDto002) throws Exception {
        return 0;
    }

    // 일정 공개범위 데이터 insert 메소드 (TABLE NAME : t_sc_sch_user / USER_TYPE : 20 (참여자))
    @Override
    public int createDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        log.info("insertSchDisclosureScopes Method Start !!!");
        log.info("insertSchDisclosureScopes Method Request Data : " + schRqDto002);

        // 공개범위 변수 선언
        List<SchRqDto002.DisclosureScope> disclosureScopes = schRqDto002.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            log.info("insertSchDisclosureScopes Method disclosureScopes == null or disclosureScopes.size() == 0");
            return 0;
        }
        // 단일 일정에 포함된 공개범위 등록
        int insertRow = schMapper002.insertDisclosureScopes(schRqDto002);

        log.info("insertSchDisclosureScopes Method Return Data : " + insertRow);
        log.info("insertSchDisclosureScopes Method End !!!");
        // return
        return insertRow;
    }

    @Override
    public int modifyDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        return 0;
    }

    @Override
    public int removeDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        return 0;
    }
}
