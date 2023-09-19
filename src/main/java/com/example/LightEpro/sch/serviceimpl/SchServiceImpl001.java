package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.constant.SchConstValue;
import com.example.LightEpro.sch.dto.sch001.SchRqDto001;
import com.example.LightEpro.sch.dto.sch001.SchRsDto001;
import com.example.LightEpro.sch.mapper.SchMapper001;
import com.example.LightEpro.sch.service.SchService001;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl001 implements SchService001 {

    // schMapper001 선언
    private final SchMapper001 schMapper001;

    // 단일 일정 정보 조회 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto001 findScheduleInfo(SchRqDto001 schRqDto001) throws Exception {
        // method start log
        log.info("findScheduleInfo Method Start !!!");
        log.info("findScheduleInfo Method Request Data : " + schRqDto001);

        // 단일 일정 조회 메소드 호출
        SchRsDto001.Schedule schedule = findSchedule(schRqDto001);
        // 단일 일정 참여자 조회 메소드 호출
        List<SchRsDto001.Participant> participants = findParticipants(schRqDto001);
        // 단일 일정 공개범위 조회 메소드 호출
        List<SchRsDto001.DisclosureScope> disclosureScopes = findDisclosureScopes(schRqDto001);

        // schRsDto001 객체 builder 패턴을 통해 객체 생성
        SchRsDto001 schRsDto001 = SchRsDto001.builder()
                .schedule(schedule)
                .participants(participants)
                .disclosureScopes(disclosureScopes)
                .build();

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRsDto001);

        // method end log
        log.info("findScheduleInfo Method Return Data : " + schRsDto001);
        log.info("findScheduleInfo Method End !!!");

        // return
        return schRsDto001;
    }

    // 일정 조회 메소드
    @Override
    public SchRsDto001.Schedule findSchedule(SchRqDto001 schRqDto001) throws Exception {
        // method start log
        log.info("findSchedule Method Start !!!");
        log.info("findSchedule Method Request Data : " + schRqDto001);

        // 일정 조회 Mapper 호출
        SchRsDto001.Schedule schedule = schMapper001.selectSchedule(schRqDto001);

        // method end log
        log.info("findSchedule Method Return Data : " + schedule);
        log.info("findSchedule Method End !!!");

        // return
        return schedule;
    }

    // 일정 참여자 조회 메소드
    @Override
    public List<SchRsDto001.Participant> findParticipants(SchRqDto001 schRqDto001) throws Exception {
        // method start log
        log.info("findParticipants Method Start !!!");
        log.info("findParticipants Method Request Data : " + schRqDto001);

        // 일정 참여자 조회 Mapper 호출
        List<SchRsDto001.Participant> participants = schMapper001.selectParticipants(schRqDto001);

        // method end log
        log.info("findParticipants Method Return Data : " + participants);
        log.info("findParticipants Method End !!!");

        // return
        return participants;
    }

    // 일정 공개범위 조회 메소드
    @Override
    public List<SchRsDto001.DisclosureScope> findDisclosureScopes(SchRqDto001 schRqDto001) throws Exception {
        // method start log
        log.info("findDisclosureScopes Method Start !!!");
        log.info("findDisclosureScopes Method Request Data : " + schRqDto001);

        // 일정 공개범위 조회 Mapper 호출
        List<SchRsDto001.DisclosureScope> disclosureScopes = schMapper001.selectDisclosureScopes(schRqDto001);

        // method end log
        log.info("findParticipants Method Return Data : " + disclosureScopes);
        log.info("findParticipants Method End !!!");

        // return
        return disclosureScopes;
    }

    // schRsDto001 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(SchRsDto001 schRsDto001) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRsDto001);

        // 일정 구성원 calSeq 값 재 할당 메소드 호출
        confirmScheduleUserCalendarSequence(schRsDto001);

        // method end log
        log.info("assignObject Method Return Data : " + schRsDto001);
        log.info("assignObject Method End !!!");
    }

    // 일정 구성원 calSeq 값 재 할당 메소드
    @Override
    public void confirmScheduleUserCalendarSequence(SchRsDto001 schRsDto001) throws Exception {
        // method start log
        log.info("confirmScheduleUserCalendarSequence Method Start !!!");
        log.info("confirmScheduleUserCalendarSequence Method Request Data : " + schRsDto001);

        // schRsDto001 객체로부터 schedule , participants , disclosureScopes 추출
        SchRsDto001.Schedule schedule = schRsDto001.getSchedule();
        List<SchRsDto001.Participant> participants = schRsDto001.getParticipants();
        List<SchRsDto001.DisclosureScope> disclosureScopes = schRsDto001.getDisclosureScopes();

        // participants 중 등록자를 제외한 인원에 대해  일정 등록자의 calSeq 값으로 재 할당
        for (SchRsDto001.Participant participant : participants) {
            if ((participant.getCalSeq().equals(SchConstValue.ZERO_VALUE))) {
                participant.setCalSeq(schedule.getCalSeq());
            }
        }

        // disclosureScopes null 및 size 검증 (null 이거나 size 값이 0인 경우)
        if (disclosureScopes == null || disclosureScopes.size() == 0) {
            log.info("confirmScheduleUserCalendarSequence Method disclosureScopes Data : null or size = 0");
        }
        // disclosureScopes null 및 size 검증 (null 이 아니며 size 값이 0이 아닌 경우)
        else {
            // disclosureScopes 중 등록자를 제외한 인원에 대해  일정 등록자의 calSeq 값으로 재 할당
            for (SchRsDto001.DisclosureScope disclosureScope : disclosureScopes) {
                if ((disclosureScope.getCalSeq().equals(SchConstValue.ZERO_VALUE))) {
                    disclosureScope.setCalSeq(schedule.getCalSeq());
                }
            }
        }

        // method end log
        log.info("confirmScheduleUserCalendarSequence Method Return Data : " + schRsDto001);
        log.info("confirmScheduleUserCalendarSequence Method End !!!");
    }
}
