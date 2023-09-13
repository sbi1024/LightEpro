package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;
import com.example.LightEpro.sch.mapper.SchMapper007;
import com.example.LightEpro.sch.service.SchService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl007 implements SchService007 {
    // schMapper007 선언
    private final SchMapper007 schMapper007;

    // 단일 캘린더 정보 수정 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto007 modifyCalendarInfo(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyCalendarInfo Method Start !!!");
        log.info("modifyCalendarInfo Method Request Data : " + schRqDto007);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRqDto007);

        // 캘린더 수정 메소드 호출
        int modifyCalendarCnt = modifyCalendar(schRqDto007);
        // 캘린더 소유자 수정 메소드 호출
        int modifyOwnerCnt = modifyOwner(schRqDto007);
        // 캘린더 관리자 등록 메소드 호출
        int createManagersCnt = createManagers(schRqDto007);
        // 캘린더 관리자 수정 메소드 호출
        int modifyManagersCnt = modifyManagers(schRqDto007);
        // 캘린더 관리자 삭제 메소드 호출
        int removeManagersCnt = removeManagers(schRqDto007);

        // schRsDto007 객체 builder 패턴을 통해 객체 생성
        SchRsDto007 schRsDto007 = SchRsDto007.builder()
                .calSeq(schRqDto007.getCalendar().getCalSeq())
                .modifyCalendarCnt(modifyCalendarCnt)
                .modifyOwnerCnt(modifyOwnerCnt)
                .createManagersCnt(createManagersCnt)
                .modifyManagersCnt(modifyManagersCnt)
                .removeManagersCnt(removeManagersCnt)
                .build();

        // method end log
        log.info("modifyCalendarInfo Method Return Data : " + schRsDto007);
        log.info("modifyCalendarInfo Method End !!!");

        // return
        return schRsDto007;
    }

    // schRqDto007 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto007);

        // 캘린더 소유자 및 관리자 일치 비일치 추출
        confirmCalendarUsers(schRqDto007);

        // method end log
        log.info("assignObject Method Result Data : " + schRqDto007);
        log.info("assignObject Method End !!!");
    }

    // 캘린더 구성원 (관리자) 일치 비일치 검증 메소드
    @Override
    public void confirmCalendarUsers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("confirmCalendarUsers Method Start !!!");
        log.info("confirmCalendarUsers Method Request Data : " + schRqDto007);

        // 기존에 존재하는 캘린더 관리자 데이터 / 요청값의 캘린더 관리자 데이터 추출 (만약 null 값이라면, 빈 리스트 값으로 할당)
        List<SchRqDto007.Manager> originManagers = schMapper007.selectManagers(schRqDto007) == null ?
                new ArrayList<>() : schMapper007.selectManagers(schRqDto007);
        List<SchRqDto007.Manager> requestManagers = schRqDto007.getManagers() == null ?
                new ArrayList<>() : schRqDto007.getManagers();

        // 비 일치하는 캘린더 구성원 추출 (요청값으로 받은 캘린더 관리자 <-> 기존 캘린더 관리자)
        List<SchRqDto007.Manager> requestNonMatchManagers = confirmRequestNonMatchManagers(originManagers, requestManagers);
        // 일치하는 캘린더 구성원 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
        List<SchRqDto007.Manager> originMatchManagers = confirmOriginMatchManagers(originManagers, requestManagers);
        // 비 일치하는 캘린더 구성원 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
        List<SchRqDto007.Manager> originNonMatchManagers = confirmOriginNonMatchManagers(originManagers, requestManagers);

        // 일치 비일치 공개범위 데이터 schRqDto007 객체에 재 할당
        schRqDto007.setRequestNonMatchManagers(requestNonMatchManagers);
        schRqDto007.setOriginMatchManagers(originMatchManagers);
        schRqDto007.setOriginNonMatchManagers(originNonMatchManagers);

        // method end log
        log.info("confirmCalendarUsers Method End !!!");
    }

    // 비 일치하는 캘린더 공개범위 추출 (요청값으로 받은 캘린더 관리자 <-> 기존 캘린더 관리자)
    @Override
    public List<SchRqDto007.Manager> confirmRequestNonMatchManagers(List<SchRqDto007.Manager> originManagers,
                                                                    List<SchRqDto007.Manager> requestManagers) throws Exception {
        // method start log
        log.info("confirmRequestNonMatchManagers Method Start !!!");
        log.info("confirmRequestNonMatchManagers Method originManagers : " + originManagers + " requestManagers : " + requestManagers);

        // stream을 통해 비 일치하는 캘린더 관리자 추출 (요청값으로 받은 캘린더 관리자 <-> 기존 캘린더 관리자)
        List<SchRqDto007.Manager> requestNonMatchManagers = requestManagers.stream()
                .filter(requestManager ->
                        originManagers.stream().noneMatch(
                                originManager -> (
                                        (String.valueOf(requestManager.getCdeSeq()).equals(String.valueOf(originManager.getCdeSeq())))
                                                && (String.valueOf(requestManager.getCdeType()).equals(String.valueOf(originManager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmRequestNonMatchManagers Method Result Data : " + requestNonMatchManagers);
        log.info("confirmRequestNonMatchManagers Method End !!!");

        // return
        return requestNonMatchManagers;
    }

    // 일치하는 캘린더 공개범위 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
    @Override
    public List<SchRqDto007.Manager> confirmOriginMatchManagers(List<SchRqDto007.Manager> originManagers,
                                                                List<SchRqDto007.Manager> requestManagers) throws Exception {
        // method start log
        log.info("confirmOriginMatchManagers Method Start !!!");
        log.info("confirmOriginMatchManagers Method originManagers : " + originManagers + " requestManagers : " + requestManagers);

        // stream을 통해 일치하는 캘린더 관리자 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
        List<SchRqDto007.Manager> originMatchManagers = originManagers.stream()
                .filter(originManager ->
                        requestManagers.stream().anyMatch(
                                requestManager -> (
                                        (String.valueOf(originManager.getCdeSeq()).equals(String.valueOf(requestManager.getCdeSeq())))
                                                && (String.valueOf(originManager.getCdeType()).equals(String.valueOf(requestManager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginMatchManagers Method Result Data : " + originMatchManagers);
        log.info("confirmOriginMatchManagers Method End !!!");

        return originMatchManagers;
    }

    // 비 일치하는 캘린더 공개범위 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
    @Override
    public List<SchRqDto007.Manager> confirmOriginNonMatchManagers(List<SchRqDto007.Manager> originManagers,
                                                                   List<SchRqDto007.Manager> requestManagers) throws Exception {
        // method start log
        log.info("confirmOriginNonMatchManagers Method Start !!!");
        log.info("confirmOriginNonMatchManagers Method originManagers : " + originManagers + " requestManagers : " + requestManagers);

        // stream을 통해 비 일치하는 캘린더 관리자 추출 (기존 캘린더 관리자 <-> 요청값으로 받은 캘린더 관리자)
        List<SchRqDto007.Manager> originNonMatchManagers = originManagers.stream()
                .filter(originManager ->
                        requestManagers.stream().noneMatch(
                                requestManager -> (
                                        (String.valueOf(originManager.getCdeSeq()).equals(String.valueOf(requestManager.getCdeSeq())))
                                                && (String.valueOf(originManager.getCdeType()).equals(String.valueOf(requestManager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        // method end log
        log.info("confirmOriginNonMatchManagers Method Result Data : " + originNonMatchManagers);
        log.info("confirmOriginNonMatchManagers Method End !!!");

        // return
        return originNonMatchManagers;
    }

    // 캘린더 수정 메소드
    @Override
    public int modifyCalendar(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyCalendar Method Start !!!");
        log.info("modifyCalendar Method Request Data : " + schRqDto007);

        // 캘린더 수정 Mapper 호출
        int updateCalendarCnt = schMapper007.updateCalendar(schRqDto007);

        // method end log
        log.info("modifyCalendar Method Start !!!");
        log.info("modifyCalendar Method Request Data : " + updateCalendarCnt);

        // return
        return updateCalendarCnt;
    }

    // 캘린더 소유자 수정 메소드
    @Override
    public int modifyOwner(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyOwner Method Start !!!");
        log.info("modifyOwner Method Request Data : " + schRqDto007);

        // 캘린더 소유자 수정 Mapper 호출
        int updateCalOwnerCnt = schMapper007.updateOwner(schRqDto007);

        // method end log
        log.info("modifyOwner Method Start !!!");
        log.info("modifyOwner Method Request Data : " + updateCalOwnerCnt);

        // return
        return updateCalOwnerCnt;
    }

    // 캘린더 관리자 등록 메소드
    @Override
    public int createManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + schRqDto007);

        // requestNonMatchManagers null 및 size 검증
        List<SchRqDto007.Manager> requestNonMatchManagers = schRqDto007.getRequestNonMatchManagers();
        if (requestNonMatchManagers == null || requestNonMatchManagers.size() == 0) {
            log.info("createManagers Method requestNonMatchManagers == null or requestNonMatchManagers.size() == 0");
            return 0;
        }

        // 캘린더 관리자 등록 Mapper 호출
        int insertManagersCnt = schMapper007.insertRequestNonMatchManagers(schRqDto007);

        // method end log
        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + insertManagersCnt);

        // return
        return insertManagersCnt;
    }

    // 캘린더 관리자 수정 메소드
    @Override
    public int modifyManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + schRqDto007);

        // originMatchManagers null 및 size 검증
        List<SchRqDto007.Manager> originMatchManagers = schRqDto007.getOriginMatchManagers();
        if (originMatchManagers == null || originMatchManagers.size() == 0) {
            log.info("modifyManagers Method originMatchManagers == null or originMatchManagers.size() == 0");
            return 0;
        }

        // 캘린더 관리자 수정 Mapper 호출
        int updateManagersCnt = schMapper007.updateOriginMatchManagers(schRqDto007);

        // method end log
        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + updateManagersCnt);

        // return
        return updateManagersCnt;
    }

    // 캘린더 관리자 삭제 메소드
    @Override
    public int removeManagers(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + schRqDto007);

        // originNonMatchManagers null 및 size 검증
        List<SchRqDto007.Manager> originNonMatchManagers = schRqDto007.getOriginNonMatchManagers();
        if (originNonMatchManagers == null || originNonMatchManagers.size() == 0) {
            log.info("removeManagers Method originNonMatchManagers == null or originNonMatchManagers.size() == 0");
            return 0;
        }

        // 캘린더 관리자 삭제 Mapper 호출
        int updateManagersCnt = schMapper007.updateNonMatchManagers(schRqDto007);

        // method end log
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + updateManagersCnt);

        // return
        return updateManagersCnt;
    }
}
