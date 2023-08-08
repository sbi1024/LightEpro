package com.example.LightEpro.sch.serviceimpl;

import com.example.LightEpro.sch.dto.sch005.SchRqDto005;
import com.example.LightEpro.sch.dto.sch007.SchRqDto007;
import com.example.LightEpro.sch.dto.sch007.SchRsDto007;
import com.example.LightEpro.sch.mapper.SchMapper007;
import com.example.LightEpro.sch.service.SchService007;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchServiceImpl007 implements SchService007 {
    private final SchMapper007 schMapper007;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public SchRsDto007 modifySingleCal(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("modifySingleCal Method Start !!!");
        log.info("modifySingleCal Method Request Data : " + schRqDto007);

        // 하기 메소드 실행을 위한 객체 데이터 재 할당 진행
        int curSeq = assignObject(schRqDto007);

        // 캘린더 정보를 수정 한다
        int updateSingleCalCnt = updateSingleCal(schRqDto007);
        // 캘린더 관리자 정보를 삭제 한다
        int removeCalManagersCnt = removeCalManagers(schRqDto007);
        // 캘린더 관리자 정보를 수정 한다
        int modifyCalManagersCnt = modifyCalManagers(schRqDto007);
        // 캘린더 관리자 정보를 등록 한다
        int createCalManagersCnt = createCalManagers(schRqDto007);


        // schRsDto007 객체 builder 패턴을 통해 객체 생성
        SchRsDto007 schRsDto007 = SchRsDto007.builder()
                .calSeq(curSeq)
                .updateSingleCalCnt(updateSingleCalCnt)
                .removeCalManagersCnt(removeCalManagersCnt)
                .modifyCalManagersCnt(modifyCalManagersCnt)
                .createCalManagersCnt(createCalManagersCnt)
                .build();

        // method end log
        log.info("modifySingleCal Method Return Data : " + schRsDto007);
        log.info("modifySingleCal Method End !!!");

        // return
        return schRsDto007;
    }

    @Override
    public int assignObject(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("assignObject Method Start !!!");
        log.info("assignObject Method Request Data : " + schRqDto007);

        // 캘린더 구성원 일치/비일치 검증
        checkCalUsers(schRqDto007);

        // schRqDto007 객체의 멤버 변수 추출
        SchRqDto007.Emp emp = schRqDto007.getEmp();
        SchRqDto007.Calender calender = schRqDto007.getCalender();
        List<SchRqDto007.Manager> managers = schRqDto007.getManagers();

        int empSeq = emp.getEmpSeq(); // empSeq 값 추출

        // 캘린더 최소 등록관련 정보 조회
        SchRqDto007.Calender createInfo = schMapper007.findCalCreateInfoByCalSeq(calender);
        int curSeq = calender.getCalSeq(); // 캘린더 시퀀스
        int createSeq = createInfo.getCreateSeq(); // 캘린더 등록자 시퀀스
        LocalDateTime createDate = createInfo.getCreateDate(); // 캘린더 등록일자

        calender.setCreateSeq(createSeq);
        calender.setCreateDate(createDate);
        calender.setModifySeq(empSeq);

        // 캘린더 관리자 데이터 재 할당
        if (managers != null && managers.size() > 0) {
            for (SchRqDto007.Manager manager : managers) {
                manager.setCalSeq(curSeq);
                manager.setCreateDate(createDate);
                manager.setCreateSeq(createSeq);
                manager.setModifySeq(empSeq);
            }
        }

        // method end log
        log.info("assignObject Method Result Data : " + curSeq);
        log.info("assignObject Method End !!!");
        return curSeq;
    }

    @Override
    public void checkCalUsers(SchRqDto007 schRqDto007) throws Exception {
        log.info("checkCalUsers Method Start !!!");
        log.info("checkCalUsers Method Request Data : " + schRqDto007);

        // 기존에 존재하는 캘린더 구성원 데이터 (소유자 정보 제외)
        List<SchRqDto007.CalendarUser> calendarUsers = schMapper007.findCalUsersInfoByCalSeq(schRqDto007);
        List<SchRqDto007.Manager> managers = schRqDto007.getManagers();
        // 요청값의 캘린더 관리자 정보가 null 이거나 size 0 인경우 메소드 return
        if (managers == null || managers.size() == 0) {
            return;
        }

        // 일치하는 캘린더 구성원 추출 (기존 캘린더 구성원 <-> 변경하고자 하는 캘린더 구성원)
        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = checkOriginMatchCalUsers(calendarUsers, managers);
        // 비 일치하는 캘린더 구성원 추출 (기존 캘린더 구성원 <-> 변경하고자 하는 캘린더 구성원)
        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = checkOriginNonMatchCalUsers(calendarUsers, managers);
        // 비 일치하는 캘린더 구성원 추출 (변경하고자 하는 캘린더 구성원 <-> 기존 캘린더 구성원)
        List<SchRqDto007.Manager> newNonMatchCalendarUsers = checkNewNonMatchCalUsers(calendarUsers, managers);

        // 요청값에 일치 / 비일치 캘린더 구성원 재 할당
        schRqDto007.setOriginMatchCalendarUsers(originMatchCalendarUsers);
        schRqDto007.setOriginNonMatchCalendarUsers(originNonMatchCalendarUsers);
        schRqDto007.setNewNonMatchCalendarUsers(newNonMatchCalendarUsers);

        // method end log
        log.info("checkCalUsers Method End !!!");
    }

    // 수정 처리 (update)
    @Override
    public List<SchRqDto007.CalendarUser> checkOriginMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                                   List<SchRqDto007.Manager> managers) throws Exception {
        // 반복문을 통해 일치 데이터 확인
        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = calendarUsers.stream()
                .filter(calUser ->
                        managers.stream().anyMatch(
                                manager -> (
                                        (String.valueOf(calUser.getCdeSeq()).equals(String.valueOf(manager.getCdeSeq())))
                                                && (String.valueOf(calUser.getCdeType()).equals(String.valueOf(manager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());

        return originMatchCalendarUsers;
    }

    // 삭제 처리 (delete)
    @Override
    public List<SchRqDto007.CalendarUser> checkOriginNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                                      List<SchRqDto007.Manager> managers) throws Exception {
        // 반복문을 통해 비 일치 데이터 확인
        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = calendarUsers.stream()
                .filter(calUser ->
                        managers.stream().noneMatch(
                                manager -> (
                                        (String.valueOf(calUser.getCdeSeq()).equals(String.valueOf(manager.getCdeSeq())))
                                                && (String.valueOf(calUser.getCdeType()).equals(String.valueOf(manager.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());
        return originNonMatchCalendarUsers;
    }

    // 등록 처리 (insert)
    @Override
    public List<SchRqDto007.Manager> checkNewNonMatchCalUsers(List<SchRqDto007.CalendarUser> calendarUsers,
                                                              List<SchRqDto007.Manager> managers) throws Exception {
        // 반복문을 통해 비 일치 데이터 확인
        List<SchRqDto007.Manager> newNonMatchCalendarUsers = managers.stream()
                .filter(manager ->
                        calendarUsers.stream().noneMatch(
                                calUser -> (
                                        (String.valueOf(manager.getCdeSeq()).equals(String.valueOf(calUser.getCdeSeq())))
                                                && (String.valueOf(manager.getCdeType()).equals(String.valueOf(calUser.getCdeType())))
                                )
                        )
                )
                .collect(Collectors.toList());
        return newNonMatchCalendarUsers;
    }

    @Override
    public int updateSingleCal(SchRqDto007 schRqDto007) throws Exception {
        // method start log
        log.info("updateSingleCal Method Start !!!");
        log.info("updateSingleCal Method Request Data : " + schRqDto007);

        int updateSingleCalCnt = schMapper007.updateSingleCal(schRqDto007);

        // method end log
        log.info("updateSingleCal Method Start !!!");
        log.info("updateSingleCal Method Request Data : " + updateSingleCalCnt);

        // return
        return updateSingleCalCnt;
    }

    @Override
    public int removeCalManagers(SchRqDto007 schRqDto007) throws Exception {
        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.CalendarUser> originNonMatchCalendarUsers = schRqDto007.getOriginNonMatchCalendarUsers();

        log.info("removeCalManagers Method Start !!!");
        log.info("removeCalManagers Method Request Data : " + schRqDto007);
        return 0;
    }

    @Override
    public int modifyCalManagers(SchRqDto007 schRqDto007) throws Exception {
        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.CalendarUser> originMatchCalendarUsers = schRqDto007.getOriginMatchCalendarUsers();

        log.info("modifyCalManagers Method Start !!!");
        log.info("modifyCalManagers Method Request Data : " + schRqDto007);
        return 0;
    }

    @Override
    public int createCalManagers(SchRqDto007 schRqDto007) throws Exception {
        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + schRqDto007);

        List<SchRqDto007.Manager> newNonMatchCalendarUsers = schRqDto007.getNewNonMatchCalendarUsers();

        log.info("createCalManagers Method Start !!!");
        log.info("createCalManagers Method Request Data : " + schRqDto007);
        return 0;
    }
}
