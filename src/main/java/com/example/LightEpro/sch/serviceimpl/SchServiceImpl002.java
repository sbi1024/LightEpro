package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch002.SchRqDto002;
import com.example.LightEpro.sch.dto.sch002.SchRsDto002;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.mapper.SchMapper002;
import com.example.LightEpro.sch.service.SchService002;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl002 implements SchService002 {
    // schMapper002 선언
    private final SchMapper002 schMapper002;

    // 단일 일정 정보 수정 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto002 modifyScheduleInfo(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("modifyScheduleInfo Method Start !!!");
        log.info("modifyScheduleInfo Method Request Data : " + schRqDto002);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRqDto002);

        // 일정 수정 메소드 호출
        int modifyScheduleCnt = modifySchedule(schRqDto002);

        // 일정 참여자 등록 메소드 호출
        int createParticipantsCnt = createParticipants(schRqDto002);
        // 일정 참여자 수정 메소드 호출
        int modifyParticipantsCnt = modifyParticipants(schRqDto002);
        // 일정 참여자 삭제 메소드 호출
        int removeParticipantsCnt = removeParticipants(schRqDto002);

        // 일정 공개범위 등록 메소드 호출
        int createDisclosureScopesCnt = createDisclosureScopes(schRqDto002);
        // 일정 공개범위 수정 메소드 호출
        int modifyDisclosureScopesCnt = modifyDisclosureScopes(schRqDto002);
        // 일정 공개범위 삭제 메소드 호출
        int removeDisclosureScopesCnt = removeDisclosureScopes(schRqDto002);

        // schRsDto002 객체 builder 패턴을 통해 객체 생성
        SchRsDto002 schRsDto002 = SchRsDto002.builder()
                .schmSeq(schRqDto002.getSchedule().getSchmSeq())
                .schSeq(schRqDto002.getSchedule().getSchSeq())
                .modifyScheduleCnt(modifyScheduleCnt)
                .createParticipantsCnt(createParticipantsCnt)
                .modifyParticipantsCnt(modifyParticipantsCnt)
                .removeParticipantsCnt(removeParticipantsCnt)
                .createDisclosureScopesCnt(createDisclosureScopesCnt)
                .modifyDisclosureScopesCnt(modifyDisclosureScopesCnt)
                .removeDisclosureScopesCnt(removeDisclosureScopesCnt)
                .build();

        // method end log
        log.info("modifyScheduleInfo Method Return Data : " + schRsDto002);
        log.info("modifyScheduleInfo Method End !!!");

        // return
        return schRsDto002;
    }


    // schRqDto002 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto002);

        // 일정 객체에 일정 일자 값 할당 메소드 (startDate , endDate)
        confirmScheduleDate(schRqDto002);
        // 일정 참여자 및 공개범위 일치 비일치 추출
        confirmScheduleUsers(schRqDto002);

        // method end log
        log.info("assignObject Method Result Data : " + schRqDto002);
        log.info("assignObject Method End !!!");
    }

    // 일정 객체에 일정 일자 값 할당 메소드 (startDate , endDate)
    @Override
    public void confirmScheduleDate(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("confirmScheduleDate Method Start !!!");
        log.info("confirmScheduleDate Method Request Data : " + schRqDto002);

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

        // method end log
        log.info("confirmScheduleDate Method End !!!");
    }

    // 일정 구성원 (참여자/공개범위) 일치 비일치 검증 메소드
    @Override
    public void confirmScheduleUsers(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("confirmScheduleUsers Method Start !!!");
        log.info("confirmScheduleUsers Method Request Data : " + schRqDto002);

        // 기존에 존재하는 일정 참여자 데이터 / 요청값의 일정 참여자 데이터 추출
        List<SchRqDto002.Participant> originParticipants = schMapper002.selectParticipants(schRqDto002);
        List<SchRqDto002.Participant> requestParticipants = schRqDto002.getParticipants();

        // 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
        List<SchRqDto002.Participant> originMatchParticipants = confirmOriginMatchParticipants(originParticipants, requestParticipants);
        // 비 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
        List<SchRqDto002.Participant> originNonMatchParticipants = confirmOriginNonMatchParticipants(originParticipants, requestParticipants);
        // 비 일치하는 일정 참여자 추출 (요청값으로 받은 일정 참여자 <-> 기존 일정 참여자)
        List<SchRqDto002.Participant> requestNonMatchParticipants = confirmRequestNonMatchParticipants(originParticipants, requestParticipants);

        // 일치 비일치 참여자 데이터 schRqDto002 객체에 재 할당
        schRqDto002.setOriginMatchParticipants(originMatchParticipants);
        schRqDto002.setOriginNonMatchParticipants(originNonMatchParticipants);
        schRqDto002.setRequestNonMatchParticipants(requestNonMatchParticipants);


        // 요청값으로 받은 참여자 및 공개범위 데이터 추출 (만약 null 값이라면, 빈 리스트 값으로 할당)
        List<SchRqDto002.DisclosureScope> originDisclosureScopes = schMapper002.selectDisclosureScopes(schRqDto002) == null ?
                new ArrayList<>() : schMapper002.selectDisclosureScopes(schRqDto002);
        List<SchRqDto002.DisclosureScope> requestDisclosureScopes = schRqDto002.getDisclosureScopes() == null ?
                new ArrayList<>() : schRqDto002.getDisclosureScopes();

        // 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
        List<SchRqDto002.DisclosureScope> originMatchDisclosureScopes = confirmOriginMatchDisclosureScopes(originDisclosureScopes, requestDisclosureScopes);
        // 비 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
        List<SchRqDto002.DisclosureScope> originNonMatchDisclosureScopes = confirmOriginNonMatchDisclosureScopes(originDisclosureScopes, requestDisclosureScopes);
        // 비 일치하는 일정 공개범위 추출 (요청값으로 받은 일정 공개범위 <-> 기존 일정 공개범위)
        List<SchRqDto002.DisclosureScope> requestNonMatchDisclosureScopes = confirmRequestNonMatchDisclosureScopes(originDisclosureScopes, requestDisclosureScopes);

        // 일치 비일치 공개범위 데이터 schRqDto002 객체에 재 할당
        schRqDto002.setOriginMatchDisclosureScopes(originMatchDisclosureScopes);
        schRqDto002.setOriginNonMatchDisclosureScopes(originNonMatchDisclosureScopes);
        schRqDto002.setRequestNonMatchDisclosureScopes(requestNonMatchDisclosureScopes);

        // method end log
        log.info("confirmScheduleUsers Method End !!!");
    }

    // 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
    @Override
    public List<SchRqDto002.Participant> confirmOriginMatchParticipants(List<SchRqDto002.Participant> originParticipants,
                                                                        List<SchRqDto002.Participant> requestParticipants) throws Exception {
        // method start log
        log.info("confirmOriginMatchParticipants Method Start !!!");
        log.info("confirmOriginMatchParticipants Method originParticipants : " + originParticipants + " requestParticipants : " + requestParticipants);

        // stream 을 통해 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
        List<SchRqDto002.Participant> originMatchParticipants = originParticipants.stream()
                .filter(originParticipant ->
                        requestParticipants.stream().anyMatch(
                                requestParticipant -> (
                                        (String.valueOf(originParticipant.getCdeSeq()).equals(String.valueOf(requestParticipant.getCdeSeq())))
                                                && (String.valueOf(originParticipant.getCdeType()).equals(String.valueOf(requestParticipant.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginMatchParticipants Method Result Data : " + originMatchParticipants);
        log.info("confirmOriginMatchParticipants Method End !!!");

        // return
        return originMatchParticipants;
    }

    // 비 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
    @Override
    public List<SchRqDto002.Participant> confirmOriginNonMatchParticipants(List<SchRqDto002.Participant> originParticipants,
                                                                           List<SchRqDto002.Participant> requestParticipants) throws Exception {
        // method start log
        log.info("confirmOriginNonMatchParticipants Method Start !!!");
        log.info("confirmOriginNonMatchParticipants Method originParticipants : " + originParticipants + " requestParticipants : " + requestParticipants);

        // stream 을 통해 비 일치하는 일정 참여자 추출 (기존 일정 참여자 <-> 요청값으로 받은 일정 참여자)
        List<SchRqDto002.Participant> originNonMatchParticipants = originParticipants.stream()
                .filter(originParticipant ->
                        requestParticipants.stream().noneMatch(
                                requestParticipant -> (
                                        (String.valueOf(originParticipant.getCdeSeq()).equals(String.valueOf(requestParticipant.getCdeSeq())))
                                                && (String.valueOf(originParticipant.getCdeType()).equals(String.valueOf(requestParticipant.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginNonMatchParticipants Method Result Data : " + originNonMatchParticipants);
        log.info("confirmOriginNonMatchParticipants Method End !!!");

        // return
        return originNonMatchParticipants;
    }

    // 비 일치하는 일정 참여자 추출 (요청값으로 받은 일정 참여자 <-> 기존 일정 참여자)
    @Override
    public List<SchRqDto002.Participant> confirmRequestNonMatchParticipants(List<SchRqDto002.Participant> originParticipants,
                                                                            List<SchRqDto002.Participant> requestParticipants) throws Exception {
        // method start log
        log.info("confirmRequestNonMatchParticipants Method Start !!!");
        log.info("confirmRequestNonMatchParticipants Method originParticipants : " + originParticipants + " requestParticipants : " + requestParticipants);

        // stream 을 통해 비 일치하는 일정 참여자 추출 (요청값으로 받은 일정 참여자 <-> 기존 일정 참여자)
        List<SchRqDto002.Participant> requestNonMatchParticipants = requestParticipants.stream()
                .filter(requestParticipant ->
                        originParticipants.stream().noneMatch(
                                originParticipant -> (
                                        (String.valueOf(requestParticipant.getCdeSeq()).equals(String.valueOf(originParticipant.getCdeSeq())))
                                                && (String.valueOf(requestParticipant.getCdeType()).equals(String.valueOf(originParticipant.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmRequestNonMatchParticipants Method Result Data : " + requestNonMatchParticipants);
        log.info("confirmRequestNonMatchParticipants Method End !!!");

        // return
        return requestNonMatchParticipants;
    }

    // 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
    @Override
    public List<SchRqDto002.DisclosureScope> confirmOriginMatchDisclosureScopes(List<SchRqDto002.DisclosureScope> originDisclosureScopes,
                                                                                List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception {
        // method start log
        log.info("confirmOriginMatchDisclosureScopes Method Start !!!");
        log.info("confirmOriginMatchDisclosureScopes Method originDisclosureScopes : " + originDisclosureScopes + " requestDisclosureScopes : " + requestDisclosureScopes);

        // stream 을 통해 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
        List<SchRqDto002.DisclosureScope> originMatchDisclosureScopes = originDisclosureScopes.stream()
                .filter(originDisclosureScope ->
                        requestDisclosureScopes.stream().anyMatch(
                                requestDisclosureScope -> (
                                        (String.valueOf(originDisclosureScope.getCdeSeq()).equals(String.valueOf(requestDisclosureScope.getCdeSeq())))
                                                && (String.valueOf(originDisclosureScope.getCdeType()).equals(String.valueOf(requestDisclosureScope.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginMatchDisclosureScopes Method Result Data : " + originMatchDisclosureScopes);
        log.info("confirmOriginMatchDisclosureScopes Method End !!!");

        // return
        return originMatchDisclosureScopes;
    }

    // 비 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
    @Override
    public List<SchRqDto002.DisclosureScope> confirmOriginNonMatchDisclosureScopes(List<SchRqDto002.DisclosureScope> originDisclosureScopes,
                                                                                   List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception {
        // method start log
        log.info("confirmOriginNonMatchDisclosureScopes Method Start !!!");
        log.info("confirmOriginNonMatchDisclosureScopes Method originDisclosureScopes : " + originDisclosureScopes + " requestDisclosureScopes : " + requestDisclosureScopes);

        // stream 을 통해 비 일치하는 일정 공개범위 추출 (기존 일정 공개범위 <-> 요청값으로 받은 일정 공개범위)
        List<SchRqDto002.DisclosureScope> originNonMatchDisclosureScopes = originDisclosureScopes.stream()
                .filter(originDisclosureScope ->
                        requestDisclosureScopes.stream().noneMatch(
                                requestDisclosureScope -> (
                                        (String.valueOf(originDisclosureScope.getCdeSeq()).equals(String.valueOf(requestDisclosureScope.getCdeSeq())))
                                                && (String.valueOf(originDisclosureScope.getCdeType()).equals(String.valueOf(requestDisclosureScope.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginNonMatchDisclosureScopes Method Result Data : " + originNonMatchDisclosureScopes);
        log.info("confirmOriginNonMatchDisclosureScopes Method End !!!");

        // return
        return originNonMatchDisclosureScopes;
    }

    // 비 일치하는 일정 공개범위 추출 (요청값으로 받은 일정 공개범위 <-> 기존 일정 공개범위)
    @Override
    public List<SchRqDto002.DisclosureScope> confirmRequestNonMatchDisclosureScopes(List<SchRqDto002.DisclosureScope> originDisclosureScopes,
                                                                                    List<SchRqDto002.DisclosureScope> requestDisclosureScopes) throws Exception {
        // method start log
        log.info("confirmRequestNonMatchDisclosureScopes Method Start !!!");
        log.info("confirmRequestNonMatchDisclosureScopes Method originDisclosureScopes : " + originDisclosureScopes + " requestDisclosureScopes : " + requestDisclosureScopes);

        // stream 을 통해 비 일치하는 일정 공개범위 추출 (요청값으로 받은 일정 공개범위 <-> 기존 일정 공개범위)
        List<SchRqDto002.DisclosureScope> requestNonMatchDisclosureScopes = requestDisclosureScopes.stream()
                .filter(requestDisclosureScope ->
                        originDisclosureScopes.stream().noneMatch(
                                originDisclosureScope -> (
                                        (String.valueOf(requestDisclosureScope.getCdeSeq()).equals(String.valueOf(originDisclosureScope.getCdeSeq())))
                                                && (String.valueOf(requestDisclosureScope.getCdeType()).equals(String.valueOf(originDisclosureScope.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmRequestNonMatchDisclosureScopes Method Result Data : " + requestNonMatchDisclosureScopes);
        log.info("confirmRequestNonMatchDisclosureScopes Method End !!!");

        // return
        return requestNonMatchDisclosureScopes;
    }

    // 일정 수정 메소드
    @Override
    public int modifySchedule(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("updateSingleSch Method Start !!!");
        log.info("updateSingleSch Method Request Data : " + schRqDto002);

        int updateScheduleCnt = schMapper002.updateSchedule(schRqDto002);

        // method end log
        log.info("updateSingleSch Method Return Data : " + updateScheduleCnt);
        log.info("updateSingleSch Method End !!!");

        // return
        return updateScheduleCnt;
    }

    // 일정 참여자 등록 메소드
    @Override
    public int createParticipants(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("createParticipants Method Start !!!");
        log.info("createParticipants Method Request Data : " + schRqDto002);

        // requestNonMatchParticipants 변수 추출 및 유효성 체크
        List<SchRqDto002.Participant> requestNonMatchParticipants = schRqDto002.getRequestNonMatchParticipants();
        if (requestNonMatchParticipants == null || requestNonMatchParticipants.size() == 0) {
            log.info("createParticipants Method requestNonMatchParticipants == null or requestNonMatchParticipants.size() == 0");
            return 0;
        }

        int insertParticipantsCnt = schMapper002.insertRequestNonMatchParticipants(schRqDto002);

        // method end log
        log.info("createParticipants Method Return Data : " + insertParticipantsCnt);
        log.info("createParticipants Method End !!!");

        // return
        return insertParticipantsCnt;
    }

    // 일정 참여자 수정 메소드
    @Override
    public int modifyParticipants(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("modifyParticipants Method Start !!!");
        log.info("modifyParticipants Method Request Data : " + schRqDto002);

        // originMatchParticipants 변수 추출 및 유효성 체크
        List<SchRqDto002.Participant> originMatchParticipants = schRqDto002.getOriginMatchParticipants();
        if (originMatchParticipants == null || originMatchParticipants.size() == 0) {
            log.info("createParticipants Method originMatchParticipants == null or originMatchParticipants.size() == 0");
            return 0;
        }

        int updateParticipantsCnt = schMapper002.updateOriginMatchParticipants(schRqDto002);

        // method end log
        log.info("modifyParticipants Method Return Data : " + updateParticipantsCnt);
        log.info("modifyParticipants Method End !!!");

        // return
        return updateParticipantsCnt;
    }

    // 일정 참여자 삭제 메소드
    @Override
    public int removeParticipants(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("removeParticipants Method Start !!!");
        log.info("removeParticipants Method Request Data : " + schRqDto002);

        // originNonMatchParticipants 변수 추출 및 유효성 체크
        List<SchRqDto002.Participant> originNonMatchParticipants = schRqDto002.getOriginNonMatchParticipants();
        if (originNonMatchParticipants == null || originNonMatchParticipants.size() == 0) {
            log.info("removeParticipants Method originNonMatchParticipants == null or originNonMatchParticipants.size() == 0");
            return 0;
        }

        int updateParticipantsCnt = schMapper002.updateOriginNonMatchParticipants(schRqDto002);

        // method end log
        log.info("removeParticipants Method Return Data : " + updateParticipantsCnt);
        log.info("removeParticipants Method End !!!");

        // return
        return updateParticipantsCnt;
    }

    // 일정 공개범위 등록 메소드
    @Override
    public int createDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("createDisclosureScopes Method Start !!!");
        log.info("createDisclosureScopes Method Request Data : " + schRqDto002);

        List<SchRqDto002.DisclosureScope> requestNonMatchDisclosureScopes = schRqDto002.getRequestNonMatchDisclosureScopes();
        if (requestNonMatchDisclosureScopes == null || requestNonMatchDisclosureScopes.size() == 0) {
            log.info("createDisclosureScopes Method disclosureScopes == null or disclosureScopes.size() == 0");
            return 0;
        }

        int insertDisclosureScopesCnt = schMapper002.insertRequestNonMatchDisclosureScopes(schRqDto002);

        // method end log
        log.info("createDisclosureScopes Method Return Data : " + insertDisclosureScopesCnt);
        log.info("createDisclosureScopes Method End !!!");

        // return
        return insertDisclosureScopesCnt;
    }

    // 일정 공개범위 수정 메소드
    @Override
    public int modifyDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("modifyDisclosureScopes Method Start !!!");
        log.info("modifyDisclosureScopes Method Request Data : " + schRqDto002);

        List<SchRqDto002.DisclosureScope> originMatchDisclosureScopes = schRqDto002.getOriginMatchDisclosureScopes();
        if (originMatchDisclosureScopes == null || originMatchDisclosureScopes.size() == 0) {
            log.info("modifyDisclosureScopes Method originMatchDisclosureScopes == null or originMatchDisclosureScopes.size() == 0");
            return 0;
        }

        int updateOriginMatchDisclosureScopes = schMapper002.updateOriginMatchDisclosureScopes(schRqDto002);

        // method end log
        log.info("modifyDisclosureScopes Method Return Data : " + updateOriginMatchDisclosureScopes);
        log.info("modifyDisclosureScopes Method End !!!");

        // return
        return updateOriginMatchDisclosureScopes;
    }

    // 일정 공개범위 삭제 메소드
    @Override
    public int removeDisclosureScopes(SchRqDto002 schRqDto002) throws Exception {
        // method start log
        log.info("removeDisclosureScopes Method Start !!!");
        log.info("removeDisclosureScopes Method Request Data : " + schRqDto002);

        List<SchRqDto002.DisclosureScope> originNonMatchDisclosureScopes = schRqDto002.getOriginNonMatchDisclosureScopes();
        if (originNonMatchDisclosureScopes == null || originNonMatchDisclosureScopes.size() == 0) {
            log.info("removeDisclosureScopes Method originNonMatchDisclosureScopes == null or originNonMatchDisclosureScopes.size() == 0");
            return 0;
        }

        int updateOriginNonMatchDisclosureScopesCnt = schMapper002.updateOriginNonMatchDisclosureScopes(schRqDto002);

        // method end log
        log.info("removeDisclosureScopes Method Return Data : " + updateOriginNonMatchDisclosureScopesCnt);
        log.info("removeDisclosureScopes Method End !!!");

        // return
        return updateOriginNonMatchDisclosureScopesCnt;
    }
}
