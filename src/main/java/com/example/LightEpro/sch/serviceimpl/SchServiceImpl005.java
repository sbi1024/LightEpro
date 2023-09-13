package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch000.SchRqDto000;
import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch005.SchRsDto005;
import com.example.LightEpro.sch.mapper.SchMapper005;
import com.example.LightEpro.sch.service.SchService005;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl005 implements SchService005 {

    // schMapper005 선언
    private final SchMapper005 schMapper005;

    // 단일 캘린더 정보 등록 메소드
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto005 createCalendarInfo(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("createCalendarInfo Method Start !!!");
        log.info("createCalendarInfo Method Request Data : " + schRqDto005);

        // 객체 데이터 재 주입 메소드 호출
        assignObject(schRqDto005);

        // 캘린더 등록 메소드 호출
        int createCalendarCnt = createCalendar(schRqDto005);
        // 캘린더 소유자 등록 메소드 호출
        int createOwnerCnt = createOwner(schRqDto005);
        // 캘린더 관리자 등록 메소드 호출
        int createManagersCnt = createManagers(schRqDto005);

        // schRsDto005 객체 builder 패턴을 통해 객체 생성
        SchRsDto005 schRsDto005 = SchRsDto005.builder()
                .calSeq(schRqDto005.getCalendar().getCalSeq())
                .createCalendarCnt(createCalendarCnt)
                .createOwnerCnt(createOwnerCnt)
                .createManagersCnt(createManagersCnt)
                .build();

        // method end log
        log.info("createCalendarInfo Method Return Data : " + schRsDto005);
        log.info("createCalendarInfo Method End !!!");

        // return
        return schRsDto005;
    }

    // schRqDto005 객체 데이터 재 주입 메소드
    @Override
    public void assignObject(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto005);

        // 캘린더 시퀀스 값 할당 메소드 호출
        confirmCalendarSequence(schRqDto005);

        // method end log
        log.info("assignObject Method Result Data : " + schRqDto005);
        log.info("assignObject Method End !!!");
    }

    // 캘린더 객체에 캘린더 시퀀스 값 할당 메소드
    @Override
    public void confirmCalendarSequence(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("confirmCalendarSequence Method Start !!!");
        log.info("confirmCalendarSequence Method Request Data : " + schRqDto005);

        // 캘린더 객체 생성
        SchRqDto005.Calendar calendar = schRqDto005.getCalendar();
        // 캘린더 시퀀스 값 채번
        int findCalendarSequenceValue = findCalendarSequence();
        // calendar 객체에 캘린더 시퀀스 할당
        calendar.setCalSeq(findCalendarSequenceValue);

        // method end log
        log.info("confirmCalendarSequence Method End !!!");
    }

    // 캘린더 시퀀스 번호 채번 메소드
    @Override
    public int findCalendarSequence() throws Exception {
        // method start log
        log.info("findCurrentCalValue Method Start !!!");

        // 캘린더 시퀀스 채번
        int selectCalendarSequenceValue = schMapper005.selectCalendarSequence();

        // method end log
        log.info("findCurrentCalValue Method Return Data : " + selectCalendarSequenceValue);
        log.info("findCurrentCalValue Method End !!!");

        // return
        return selectCalendarSequenceValue;
    }

    // 캘린더 등록 메소드
    @Override
    public int createCalendar(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("insertSingleCalendar Method Start !!!");
        log.info("insertSingleCalendar Method Request Data : " + schRqDto005);

        // 캘린더 등록 Mapper 호출
        int insertCalendarCnt = schMapper005.insertCalendar(schRqDto005);

        // method end log
        log.info("insertSingleCalendar Method Return Data : " + insertCalendarCnt);
        log.info("insertSingleCalendar Method End !!!");

        // return
        return insertCalendarCnt;
    }

    // 캘린더 소유자 등록 메소드
    @Override
    public int createOwner(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("insertCalOwner Method Start !!!");
        log.info("insertCalOwner Method Request Data : " + schRqDto005);

        // 캘린더 소유자 등록 Mapper 호출
        int insertCalendarOwnerCnt = schMapper005.insertCalendarOwner(schRqDto005);

        // method end log
        log.info("insertCalOwner Method Return Data : " + insertCalendarOwnerCnt);
        log.info("insertCalOwner Method End !!!");

        // return
        return insertCalendarOwnerCnt;
    }

    // 캘린더 관리자 등록 메소드
    @Override
    public int createManagers(SchRqDto005 schRqDto005) throws Exception {
        // method start log
        log.info("createManagers Method Start !!!");
        log.info("createManagers Method Request Data : " + schRqDto005);

        // managers null 및 size 검증
        List<SchRqDto005.Manager> managers = schRqDto005.getManagers();
        if (managers == null || managers.size() == 0) {
            log.info("createManagers Method managers Data == null || managers.size() == 0");
            return 0;
        }

        // 캘린더 공개범위 등록 Mapper 호출
        int insertCalendarManagersCnt = schMapper005.insertCalendarManagers(schRqDto005);

        // method end log
        log.info("createManagers Method Return Data : " + insertCalendarManagersCnt);
        log.info("createManagers Method End !!!");

        // return
        return insertCalendarManagersCnt;
    }
}
