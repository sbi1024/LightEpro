package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch000.SchRsDto000;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.mapper.SchMapper000;
import com.example.LightEpro.sch.service.SchService000;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl000 implements SchService000 {

    // schMapper000 선언
    private final SchMapper000 schMapper000;

    // 단일 일정 정보 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto000 createScheduleInfo(SchRqDto000 schRqDto000) throws Exception {
        // method start log
        log.info("createSingleSch Method Start !!!");
        log.info("createSingleSch Method Request Data : " + schRqDto000);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRqDto000);
        // 단일 일정 등록 메소드 호출
        int createScheduleCnt = createSchedule(schRqDto000);
        // 단일 일정에 포함된 참여자 등록 메소드 호출
        int createParticipantsCnt = createParticipants(schRqDto000);
        // 단일 일정에 포함된 공개범위 등록 메소드 호출
        int createDisclosureScopesCnt = createDisclosureScopes(schRqDto000);

        // schRsDto000 객체 builder 패턴을 통해 객체 생성
        SchRsDto000 schRsDto000 = SchRsDto000.builder()
                .schmSeq(schRqDto000.getSchedule().getSchmSeq())
                .schSeq(schRqDto000.getSchedule().getSchSeq())
                .createScheduleCnt(createScheduleCnt)
                .createParticipantsCnt(createParticipantsCnt)
                .createDisclosureScopesCnt(createDisclosureScopesCnt)
                .build();

        // method end log
        log.info("createSingleSch Method Return Data : " + schRsDto000);
        log.info("createSingleSch Method End !!!");

        // return
        return schRsDto000;
    }

    // schRqDto000 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(SchRqDto000 schRqDto000) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto000);

        // 일정 시퀀스 값 채번
        int findScheduleSequenceValue = findScheduleSequence();

        // 일정 시퀀스 값 할당
        SchRqDto000.Schedule schedule = schRqDto000.getSchedule();
        schedule.setSchmSeq(findScheduleSequenceValue);
        schedule.setSchSeq(findScheduleSequenceValue);

        // startDate , endDate 값 추출
        LocalDateTime startDate = schedule.getStartDate();
        LocalDateTime endDate = schedule.getEndDate();

        // year month 값 재 할당 (startDate , endDate)
        schedule.setStartDateYear(startDate.getYear());
        schedule.setStartDateMonth(startDate.getMonthValue());
        schedule.setEndDateYear(endDate.getYear());
        schedule.setEndDateMonth(endDate.getMonthValue());

        // method end log
        log.info("assignObject Method Result Data : " + schRqDto000);
        log.info("assignObject Method End !!!");
    }

    // 일정 시퀀스 번호 채번 메소드
    @Override
    public int findScheduleSequence() throws Exception {
        // method start log
        log.info("findCurrentSchValue Method Start !!!");

        // 일정 시퀀스 채번
        int selectScheduleSequenceValue = schMapper000.selectScheduleSequence();

        // method end log
        log.info("findCurrentSchValue Method Return Data : " + selectScheduleSequenceValue);
        log.info("findCurrentSchValue Method End !!!");

        // return
        return selectScheduleSequenceValue;
    }

    // 일정 등록 메소드
    @Override
    public int createSchedule(SchRqDto000 schRqDto000) throws Exception {
        // method start log
        log.info("createSchedule Method Start !!!");
        log.info("createSchedule Method Request Data : " + schRqDto000);

        // 단일 일정 등록
        int insertScheduleCnt = schMapper000.insertSchedule(schRqDto000);

        // method end log
        log.info("createSchedule Method Return Data : " + insertScheduleCnt);
        log.info("createSchedule Method End !!!");

        // return
        return insertScheduleCnt;
    }

    // 일정 참여자 등록 메소드
    @Override
    public int createParticipants(SchRqDto000 schRqDto000) throws Exception {
        // method start log
        log.info("insertSchParticipants Method Start !!!");
        log.info("insertSchParticipants Method Request Data : " + schRqDto000);

        // 참여자 변수 선언
        List<SchRqDto000.Participant> participants = schRqDto000.getParticipants();
        if (participants == null || participants.size() == 0) {
            log.info("insertSchParticipants Method participants Data : null or size = 0");
            return 0;
        }
        // 단일 일정에 포함된 참여자 등록
        int insertParticipantsCnt = schMapper000.insertParticipants(schRqDto000);

        // method end log
        log.info("insertSchParticipants Method Return Data : " + insertParticipantsCnt);
        log.info("insertSchParticipants Method End !!!");

        // return
        return insertParticipantsCnt;
    }

    // 일정 공개범위 등록 메소드
    @Override
    public int createDisclosureScopes(SchRqDto000 schRqDto000) throws Exception {
        // method start log
        log.info("insertSchDisclosureScopes Method Start !!!");
        log.info("insertSchDisclosureScopes Method Request Data : " + schRqDto000);

        // 공개범위 변수 선언
        List<SchRqDto000.DisclosureScope> disclosureScopes = schRqDto000.getDisclosureScopes();
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            log.info("insertSchDisclosureScopes Method disclosureScopes Data : null or size = 0");
            return 0;
        }

        // 단일 일정에 포함된 공개범위 등록
        int insertDisclosureScopesCnt = schMapper000.insertDisclosureScopes(schRqDto000);

        // method end log
        log.info("insertSchDisclosureScopes Method Return Data : " + insertDisclosureScopesCnt);
        log.info("insertSchDisclosureScopes Method End !!!");

        // return
        return insertDisclosureScopesCnt;
    }
}
